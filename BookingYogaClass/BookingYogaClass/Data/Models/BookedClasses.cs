using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BookingYogaClass.Data.Models
{
    public class BookedClasses
    {
        public int BookedId { get; set; }
        public int CustomerId { get; set; }
        public int SchedulesId { get; set; }
    }
}
