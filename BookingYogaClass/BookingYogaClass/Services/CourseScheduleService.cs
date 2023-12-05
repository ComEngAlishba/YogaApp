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
        // Convert a JSON string to a list of CourseSchedule objects
        public List<CourseSchedule> ConvertJsonToCourseScheduleList(string jsonString)
        {
            // Deserialize the JSON string into a list of CourseSchedule objects
            List<CourseSchedule> courseScheduleList = JsonSerializer.Deserialize<List<CourseSchedule>>(jsonString);
            return courseScheduleList;
        }

        // Get a list of available classes from the web using RestClientService
        public List<CourseSchedule> GetListOfAvailableClassesFromWeb()
        {
            // Create an instance of RestClientService to make a web request
            RestClientService client = new RestClientService();

            // Get the JSON string representing available classes from the web
            var jsonList = client.GetAsync();

            // Convert the JSON string to a list of CourseSchedule objects
            return ConvertJsonToCourseScheduleList(jsonList.Result);
        }

        // Convert a list of CourseSchedule objects to a list of ClassSchedule objects
        public List<ClassSchedule> ConvertCourseSchedulesToClassSchedules(List<CourseSchedule> courseSchedules)
        {
            // Create a new list to store ClassSchedule objects
            List<ClassSchedule> classSchedules = new List<ClassSchedule>();

            // Iterate through each CourseSchedule and convert it to a ClassSchedule
            foreach (CourseSchedule courseSchedule in courseSchedules)
            {
                // Convert CourseSchedule to ClassSchedule
                ClassSchedule classSchedule = ConvertCourseScheduleToClassSchedule(courseSchedule);

                // Add the resulting ClassSchedule to the list (if not null)
                if (classSchedule != null)
                {
                    classSchedules.Add(classSchedule);
                }
            }

            return classSchedules;
        }

        // Convert a CourseSchedule object to a ClassSchedule object
        public ClassSchedule ConvertCourseScheduleToClassSchedule(CourseSchedule courseSchedule)
        {
            // Check if the input CourseSchedule is null
            if (courseSchedule == null)
            {
                return null;
            }

            // Create a new ClassSchedule object and populate its properties
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
