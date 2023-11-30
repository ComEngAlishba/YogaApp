using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BookingYogaClass.Data
{
    public class ClassSchedule
    {
        public int ScheduleId { get; set; }
        public Course Course { get; set; }
        public int CourseId { get; set; }
        public string Date { get; set; }
        public string TeacherName { get; set; }
        public string AdditionalComments { get; set; }
    }
}
