using BookingYogaClass.Data.Models;
using BookingYogaClass.Data.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace BookingYogaClass.Services
{
    public class CourseScheduleService
    {

        public List<CourseSchedule> ConvertJsonToCourseScheduleList(string jsonString)
        {
            List<CourseSchedule> courseScheduleList = JsonSerializer.Deserialize<List<CourseSchedule>>(jsonString);
            return courseScheduleList;
        }
        public List<CourseSchedule> GetListOfAvailableClassesFromWeb()
        {
            RestClient client = new RestClient();
            var jsonList = client.GetAsync();
            return ConvertJsonToCourseScheduleList(jsonList.Result);
        }
        public List<ClassSchedule> ConvertCourseSchedulesToClassSchedules(List<CourseSchedule> courseSchedules)
        {
            List<ClassSchedule> classSchedules = new List<ClassSchedule>();

            foreach (CourseSchedule courseSchedule in courseSchedules)
            {
                ClassSchedule classSchedule = ConvertCourseScheduleToClassSchedule(courseSchedule);
                if (classSchedule != null)
                {
                    classSchedules.Add(classSchedule);
                }
            }

            return classSchedules;
        }

        public ClassSchedule ConvertCourseScheduleToClassSchedule(CourseSchedule courseSchedule)
        {
            if (courseSchedule == null)
            {
                return null;
            }

            ClassSchedule classSchedule = new ClassSchedule
            {
                InstanceId = courseSchedule.InstanceId,
                Date = courseSchedule.Date,
                Teacher = courseSchedule.Teacher,
                DayOfWeek = courseSchedule.DayOfWeek,
                TimeOfCourse = courseSchedule.Time
            };

            return classSchedule;
        }
    }
}
