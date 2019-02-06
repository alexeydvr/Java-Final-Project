using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace HumanResourceClientApp
{
    public partial class LottoNumbers : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void srButton_Click(object sender, EventArgs e)
        {
            HumanResourceServiceReference.HumanResourceXmlWebServiceClient webClient = new HumanResourceServiceReference.HumanResourceXmlWebServiceClient();

            int luckyNumber = webClient.luckyLottoNumber();
            srResultLabel.Text = luckyNumber + "";
        }

        protected void LuckyNumbers_Click(object sender, EventArgs e)
        {
            var webClient = new HumanResourceServiceReference.HumanResourceXmlWebServiceClient();
            string numbers = webClient.luckyNumbers(Convert.ToInt32(Amount.Text));
            LuckyNumberResultLabel.Text = numbers;

        }

       

        protected void AllJobs_Click(object sender, EventArgs e)
        {
            var webClient = new HumanResourceServiceReference.HumanResourceXmlWebServiceClient();
            var allJobs = webClient.findAllJobs();
            AllJobsGridView.DataSource = allJobs;
            AllJobsGridView.DataBind();
            
        }

        protected void selectJob_Click(object sender, EventArgs e)
        {
            JobDeets.Visible = true;
            var webClient = new HumanResourceServiceReference.HumanResourceXmlWebServiceClient();
            var grvRow = (GridViewRow)((Button)sender).NamingContainer;
            var jobId = grvRow.Cells[1].Text;

            var jobDetails = webClient.findOneJob(jobId);
            //TestText.Text = jobDetails.jobTitle;
            JobID.Text = jobDetails.jobId;
            Title.Text = jobDetails.jobTitle;
            MaxSalary.Text = jobDetails.maxSalary.ToString();
            MinSalary.Text = jobDetails.minSalary.ToString();


        }

        protected void SearchJob_Click(object sender, EventArgs e)
        {
            JobDeets.Visible = true;
            var webClient = new HumanResourceServiceReference.HumanResourceXmlWebServiceClient();
            var jobId = searchBox.Text;
            var jobDetails = webClient.findOneJob(jobId);
            //TestText.Text = jobDetails.jobTitle;
            JobID.Text = jobDetails.jobId;
            Title.Text = jobDetails.jobTitle;
            MaxSalary.Text = jobDetails.maxSalary.ToString();
            MinSalary.Text = jobDetails.minSalary.ToString();

            
        }



        //protected void wrButton_Click(object sender, EventArgs e)
        //{
        //    HumanResourceWebReference.HumanResourceService endPoint = new HumanResourceWebReference.HumanResourceService();
        //    int luckyNumber = endPoint.luckyLottoNumber();
        //    wrResultLabel.Text = luckyNumber + "";
        //}
    }
}