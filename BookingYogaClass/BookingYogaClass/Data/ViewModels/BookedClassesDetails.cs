using BookingYogaClass.Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BookingYogaClass.Data.ViewModels
{
    public class BookedClassesDetails
    {
        public string Username { get; set; }
        public string Email { get; set; }
        public List<ClassSchedule> CourseSchedules { get; set; } = new List<ClassSchedule>();
    }
}
