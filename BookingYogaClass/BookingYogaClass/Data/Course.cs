using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BookingYogaClass.Data
{
    public class Course
    {
        public int CourseId { get; set; }
        public string CourseName { get; set; }
        public string InstructorName { get; set; }
        public int CreditHours { get; set; }
        public string DayOfWeek { get; set; }
        public string TimeOfCourse { get; set; }
        public int Capacity { get; set; }
        public int Duration { get; set; }
        public double PricePerClass { get; set; }
        public string TypeOfClass { get; set; }
        public string Description { get; set; }
        public string Location { get; set; }
        public string DifficultyLevel { get; set; }
        public string PrerequisiteCourse { get; set; }
    }

}
