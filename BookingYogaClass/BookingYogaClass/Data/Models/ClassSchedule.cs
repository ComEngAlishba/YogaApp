using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BookingYogaClass.Data.Models
{
    public class ClassSchedule
    {
        public int ScheduleId { get; set; }
        public string DayOfWeek { get; set; }
        public string TimeOfCourse { get; set; }
        public string Date { get; set; }
        public string TeacherName { get; set; }
    }
}
