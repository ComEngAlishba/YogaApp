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
    }
}
