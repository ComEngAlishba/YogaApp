using BookingYogaClass.Data.ViewModels;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace BookingYogaClass.Services
{
    public class RestClientService
    {
        // HttpClient for making HTTP requests
        HttpClient _client;

        // JsonSerializerOptions for configuring JSON serialization
        JsonSerializerOptions _serializerOptions;

        // Response object for booking class operation
        public BookingClassResponse BookingClassResponse { get; private set; }

        // REST API endpoint URLs
        private string RestGetAllInstancesUrl =
            "https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw/GetInstances";
        private string RestSubmitBookingsUrl =
            "https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw/SubmitBookings";

        // Constructor initializes HttpClient and JsonSerializerOptions
        public RestClientService()
        {
            _client = new HttpClient();
            _serializerOptions = new JsonSerializerOptions
            {
                PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
                WriteIndented = true
            };
        }

        // Method for making a GET request to retrieve instances
        public async Task<string> GetAsync()
        {
            // Create the URI for the GET request
            Uri uri = new Uri(string.Format(RestGetAllInstancesUrl, string.Empty));

            // Initialize response content
            var instancesResponse = "";

            try
            {
                // Define the content for the POST request (in this case, a form-urlencoded string)
                StringContent content = new StringContent("userid=wm123", Encoding.UTF8, "application/x-www-form-urlencoded");

                // Send the POST request
                HttpResponseMessage response = await _client.PostAsync(uri, content);

                // Check if the response is successful
                if (response.IsSuccessStatusCode)
                {
                    // Read the response content
                    string responseContent = await response.Content.ReadAsStringAsync();
                    instancesResponse = responseContent;
                }
            }
            catch (Exception ex)
            {
                // Handle any exceptions that occur during the request
                Debug.WriteLine(@"\tERROR {0}", ex.Message);
            }

            return instancesResponse;
        }

        // Method for making a POST request to submit bookings
        public async Task<BookingClassResponse> SendEventAsync(BookedClassesDetails item)
        {
            // Create the URI for the POST request
            Uri uri = new Uri(string.Format(RestSubmitBookingsUrl, string.Empty));

            try
            {
                // Serialize the booking details to JSON
                string json = JsonSerializer.Serialize<BookedClassesDetails>(item, _serializerOptions);

                // Define the content for the POST request (in this case, a form-urlencoded string)
                StringContent content = new StringContent("jsonpayload=" + json, Encoding.UTF8, "application/x-www-form-urlencoded");

                // Send the POST request
                HttpResponseMessage response = await _client.PostAsync(uri, content);

                // Check if the response is successful
                if (response.IsSuccessStatusCode)
                {
                    // Read the response content and deserialize it to BookingClassResponse
                    string responseContent = await response.Content.ReadAsStringAsync();
                    BookingClassResponse = JsonSerializer.Deserialize<BookingClassResponse>(responseContent, _serializerOptions);
                }
            }
            catch (Exception ex)
            {
                // Handle any exceptions that occur during the request
                Debug.WriteLine(@"\tERROR {0}", ex.Message);
            }

            return BookingClassResponse;
        }
    }
}
