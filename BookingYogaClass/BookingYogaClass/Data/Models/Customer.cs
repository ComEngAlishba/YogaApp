using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BookingYogaClass.Data.Models
{
    public class Customer
    {
        public int CustomerId { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        // Constructor with parameters
        public Customer(int customerId, string firstName, string lastName, string email)
        {
            CustomerId = customerId;
            FirstName = firstName;
            LastName = lastName;
            Email = email;
        }

        // Function to convert Customer object to JSON
        public string ToJson()
        {
            return JsonConvert.SerializeObject(this, Formatting.Indented);
        }
    }

}
