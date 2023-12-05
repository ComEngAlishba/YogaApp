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


    }
}
