<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="LottoNumbers.aspx.cs" Inherits="HumanResourceClientApp.LottoNumbers" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Button ID="srButton" runat="server" Text="One Lucky Number" OnClick="srButton_Click"/>
            <asp:Label ID="srResultLabel" runat="server" />
        </div>
        <br />
        <div>
            <asp:Label AssociatedControlID="Amount" Text="How many numbers to generate: " runat="server" />
            <asp:TextBox ID="Amount" runat="server" />
            <asp:Button ID="LuckyNumbers" runat="server" Text="Many Lucky Numbers" OnClick="LuckyNumbers_Click" />
            <asp:Label ID="LuckyNumberResultLabel" runat="server" />
        </div>
        <br />
        <%--<div>
            <asp:Button ID="wrButton" runat="server" Text="Web Reference Lucky Number" OnClick="wrButton_Click"/>
            <asp:Label ID="wrResultLabel" runat="server" />
        </div>--%>
        <br />
        <div>
            <asp:Button ID="AllJobs" Text="Get All Jobs" OnClick="AllJobs_Click" runat="server" />
            <asp:GridView ID="AllJobsGridView" runat="server" AutoGenerateColumns="true">
                <Columns>
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="selectJob" runat="server" Text="Details"  OnClick="selectJob_Click"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>

        <div>
            <br />
            <asp:TextBox ID="searchBox" runat="server" />
            <asp:Button ID="SearchJob" Text="Search Job by ID" OnClick="SearchJob_Click" runat="server" />

            <asp:Panel ID="JobDeets" runat="server" Visible="false">
                <h3>Job Details:</h3>
            <asp:Label Text="<b>Job ID: </b>" AssociatedControlID="JobID" runat="server" />
            <asp:Label ID="JobID" runat="server" />
            <br />
            <asp:Label Text="<b>Job Title: </b>" AssociatedControlID="Title" runat="server" />
            <asp:Label ID="Title" runat="server" />
            <br />
            <asp:Label Text="<b>Max Salary: </b>" AssociatedControlID="MaxSalary" runat="server" />
            <asp:Label ID="MaxSalary" runat="server" />
            <br />
            <asp:Label Text="<b>Min Salary: </b>" AssociatedControlID="MinSalary" runat="server" />
            <asp:Label ID="MinSalary" runat="server" />

                <br />
                <asp:Label ID="TestLabel" runat="server" />
            </asp:Panel>
            

            
        </div>
    </form>
</body>
</html>
