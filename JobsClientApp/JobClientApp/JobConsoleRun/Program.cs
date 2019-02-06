using System;
using System.Net.Http; //for http client
using Newtonsoft.Json; //for Json conversion
namespace JobConsoleRun
{
    class Job
    {
        public string jobId { get; set; }
        public string jobTitle { get; set; }
        public int? maxSalary { get; set; }
        public int? minSalary { get; set; }

        public override string ToString()
        {
            return $"jobID: {jobId}, jobTitle: {jobTitle}, maxSalary: {maxSalary}, MinSalary: {minSalary}";
        }

    }
    class Program
    {
        const string BaseUrlString = "http://192.168.73.128:8080/dmit2015-fall2018term-project-server-start/rest/hr-api/jobs/";
        static void FetchJobsWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(BaseUrlString);
            HttpResponseMessage response = restClient.GetAsync("").Result;
            if (response.IsSuccessStatusCode)
            {
                var jsonString = response.Content.ReadAsStringAsync().Result;
                Job[] jobs = JsonConvert.DeserializeObject<Job[]>(jsonString);
                foreach (var item in jobs)
                {
                    Console.WriteLine(item);
                }
            }
            else
            {
                Console.WriteLine("Respone was not succesfull");
            }
        }

        static void FetchOneJobWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(BaseUrlString);
            Console.WriteLine("Enter JobID to fetch: ");
            string jobId = Console.ReadLine();
            HttpResponseMessage response = restClient.GetAsync(jobId).Result;
            
            if (response.IsSuccessStatusCode)
            {
                var jsonString = response.Content.ReadAsStringAsync().Result;
                Job job = JsonConvert.DeserializeObject<Job>(jsonString);
                Console.WriteLine(job);
            }
            else
            {
                Console.WriteLine("Respone was not succesfull");
            }
        }

        static void CreateOneJobWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(BaseUrlString);
            Console.WriteLine("Enter New Job ID: ");
            string tempJobId = Console.ReadLine();
            Console.WriteLine("Enter New Job Title: ");
            string tempJobTitle = Console.ReadLine();
            int tempMinSalary;
            do
            {
                Console.WriteLine("Enter minimum salary (if none enter 0): ");
            } while (!int.TryParse(Console.ReadLine(), out tempMinSalary));
            int tempMaxSalary;
            do
            {
                Console.WriteLine("Enter maximum salary (if none enter 0): ");
            } while (!int.TryParse(Console.ReadLine(), out tempMaxSalary));




            Job newJob = new Job()
            {
                jobId = tempJobId,
                jobTitle = tempJobTitle
                
                
            };
            if (tempMaxSalary != 0)
            {
                newJob.minSalary = tempMinSalary;
                newJob.maxSalary = tempMaxSalary;
            }
            string jsonJob = JsonConvert.SerializeObject(newJob);

            HttpResponseMessage response = restClient.PostAsync(BaseUrlString, new StringContent(jsonJob, System.Text.Encoding.UTF8,"application/json")).Result;
            
            if (response.IsSuccessStatusCode)
            {
                Console.WriteLine("Job created succesfully.");
            }
            else
            {
                Console.WriteLine("Job was not created.");
            }
        }

        static void UpdateOneJobWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(BaseUrlString);
            Console.WriteLine("Enter Job Id to Update: ");
            string tempJobId = Console.ReadLine();
            Console.WriteLine("Enter Updated Job Title: ");
            string tempJobTitle = Console.ReadLine();
            int tempMinSalary;
            do
            {
                Console.WriteLine("Enter minimum salary (if none enter 0): ");
            } while (!int.TryParse(Console.ReadLine(), out tempMinSalary));
            int tempMaxSalary;
            do
            {
                Console.WriteLine("Enter maximum salary (if none enter 0): ");
            } while (!int.TryParse(Console.ReadLine(), out tempMaxSalary));




            Job newJob = new Job()
            {
                jobId = tempJobId,
                jobTitle = tempJobTitle


            };
            if (tempMaxSalary != 0)
            {
                newJob.minSalary = tempMinSalary;
                newJob.maxSalary = tempMaxSalary;
            }
            string jsonJob = JsonConvert.SerializeObject(newJob);

            HttpResponseMessage response = restClient.PutAsync(BaseUrlString, new StringContent(jsonJob, System.Text.Encoding.UTF8, "application/json")).Result;

            if (response.IsSuccessStatusCode)
            {
                Console.WriteLine("Job updated succesfully.");
            }
            else
            {
                Console.WriteLine("Job was not updated.");
            }
        }

        static void DeleteOneJobWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(BaseUrlString);
            Console.WriteLine("Enter JobID to Delete: ");
            string jobId = Console.ReadLine();
            HttpResponseMessage response = restClient.DeleteAsync(BaseUrlString+jobId).Result;

            if (response.IsSuccessStatusCode)
            {
                Console.WriteLine("Delete succesfull");
            }
            else
            {
                Console.WriteLine("Delete Failed");
            }
        }

        static void Main(string[] args)
        {
            //Console.WriteLine("Hello World!");
            //FetchJobsWebService();
            //UpdateOneJobWebService();

            //CreateOneJobWebService();

            //FetchOneJobWebService();
            //DeleteOneJobWebService();
            //FetchJobsWebService();
        }
    }
}
