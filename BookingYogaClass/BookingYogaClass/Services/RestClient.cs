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
    public class RestClient
    {
        HttpClient _client;
        JsonSerializerOptions _serializerOptions;

        public RestClient()
        {
            _client = new HttpClient();
            _serializerOptions = new JsonSerializerOptions
            {
                PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
                WriteIndented = true
            };
        }

        public BookingClassResponse BookingClassResponse { get; private set; }

        private string RestGetAllInstancesUrl =
         "stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw/GetInstances";
        private string RestSubmitBookingsUrl =
        "https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw/SubmitBookings";

        public async Task<string> GetAsync()
        {
            Uri uri = new Uri(string.Format(RestGetAllInstancesUrl, string.Empty));
            var InstancesResponse = "";


            try
            {
                StringContent content = new StringContent("userid=wm123", Encoding.UTF8, "application/x-www-form-urlencoded");
                HttpResponseMessage response = null;
                response = await _client.PostAsync(uri, content);



                if (response.IsSuccessStatusCode)
                {
                    string responseContent = await response.Content.ReadAsStringAsync();
                    InstancesResponse = responseContent;
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(@"\tERROR {0}", ex.Message);
            }



            return InstancesResponse;
        }

        public async Task<BookingClassResponse> SendEventAsync(BookedClassesDetails item)
        {
            Uri uri = new Uri(string.Format(RestSubmitBookingsUrl, string.Empty));

            try
            {
                string json = JsonSerializer.Serialize<BookedClassesDetails>(item,
                  _serializerOptions);


                StringContent content = new StringContent("jsonpayload=" +
                  json, Encoding.UTF8, "application/x-www-form-urlencoded");
                HttpResponseMessage response = null;
                response = await _client.PostAsync(uri, content);

                if (response.IsSuccessStatusCode)
                {
                    string responseContent = await
                        response.Content.ReadAsStringAsync();
                    BookingClassResponse =
                        JsonSerializer.Deserialize<BookingClassResponse>(
                        responseContent, _serializerOptions);
                }
            }
            catch (Exception ex)
            { Debug.WriteLine(@"\tERROR {0}", ex.Message); }

            return BookingClassResponse;

        }
    }
    
}
