using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BookingYogaClass.Data.ViewModels
{
    public class CourseSchedule
    {
        public int InstanceId { get; set; }
        public string DayOfWeek { get; set; }
        public string Date { get; set; }
        public string Time { get; set; }
        public string Teacher { get; set; }
        public bool Selected { get; set; }
    }
}
