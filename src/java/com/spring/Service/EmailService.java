package com.spring.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.andromeda.commons.model.Response;
import com.andromeda.commons.util.FileNDirUtils;
import com.spring.Model.Email;

import com.spring.Model.Registration;

@Service
public class EmailService
{
	@Autowired
	private JavaMailSender mailSender;
	Response response = new Response();

	public boolean sendHtmlMsg(Email email)
	{
		boolean status = false;
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try
		{
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			if ((email != null) && (email.getTo() != null) && (email.getTo().length != 0)
					&& (!StringUtils.isEmpty(email.getFrom()))
					&& (!StringUtils.isEmpty(email.getText())))
			{
				helper.setFrom(email.getFrom());
				helper.setTo(email.getTo());
				helper.setSubject(email.getSubject());
				String text =
						"<html><body>&nbsp;"
								+ "<br><p>"
								+ email.getText()
								+ "</p><br>"
								+ "<b>Thanks for registration into AANM & VVRSR POLY Library,Gudlavalleru ,</b><br>"
								+ " "
								+ "<b>library Team</b><br>"
								+ "<img src='/sai/images/1.jpg' height='120' width='150' alt='Library logo'/>"
								+ "<html><body>";
				helper.setText(text, true);
			}
			if ((email != null) && (email.getCc() != null) && (email.getCc().length != 0))
			{
				helper.setCc(email.getCc());
			}
			/*
			 * if ((email != null) && (email.getBcc() != null) && (email.getBcc().length != 0)) {
			 * helper.setBcc(email.getBcc()); } if ((email != null) &&
			 * (!StringUtils.isEmpty(email.getReplyTo()))) { helper.setReplyTo(email.getReplyTo());
			 * }
			 */
			if (email.getAttachments() != null)
			{
				Iterator iterator = email.getAttachments().iterator();
				while (iterator.hasNext())
				{
					FileSystemResource file =
							new FileSystemResource(new File((String) iterator.next()));
					helper.addAttachment(file.getFilename(), file);
				}
			}
			Date date = new Date();
			helper.setSentDate(date);
			try
			{
				this.mailSender.send(mimeMessage);
				status = true;
			}
			catch (Exception ex)
			{
				status = false;
				System.err.println(ex.getMessage());
			}
		}
		catch (Exception e)
		{
			status = false;
		}
		return status;
	}
	   
	
	
	
	/*public Response mailSend(Registration registration)
	{
		this.response.setSuccessful(false);
		Email email = new Email();
		String path = System.getProperty("catalina.base") + "/webapps/ecm/attachments/"+registration.getProgramId();
		List<String> fileNames = FileNDirUtils.getFileNamesList(path, "");
		
		if(fileNames!=null){
			System.out.println("FileNames: " + fileNames);
			List<String> fileAttachments = new ArrayList<String>();
			for (int i = 0; i < fileNames.size(); i++)
			{
				String templateFileName =
						System.getProperty("catalina.base") + "/webapps/ecm/attachments/"+registration.getProgramId()+"/"
								+ fileNames.get(i);
				System.out.println("templateFileName:====>" + templateFileName);
				fileAttachments.add(templateFileName);
			}
			email.setAttachments(fileAttachments);
		}
		
		String sender = "APSSDC FIP <fip@apssdc.in>";
		email.setSubject("APSSDC - "+registration.getProgram());
		email.setFrom(sender);
		email.setTo(registration.getEmail());
		email.setName("APSSDC");
		email.setSignature("Andhra Pradesh State Skill Development Corporation (APSSDC)");
		String msg =
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram()
						+ "</b> at the <b>Skill Development Centre(SDC), RVR & JC College of Engineering(Autonomous).</b><br><br>"
						+ "<span style='color:#0000ff;'>Address:</span> Chandramoulipuram,Chowdavaram,GUNTUR-522 019,Andhra Pradesh <br><br>"
						+ "<span style='color:#0000ff;'>Reporting Time:</span> 9:00 am<br><br>"
						+ "We request you to refer 4 or 5 M.Tech (CSE/IT/ECE)/MCA final year students for internship in APSSDC (who have committed to work work with APSSDC)<br><br>"
						+ "Documents to be submitted at the venue (<span style='background-color:rgb(255,229,153)'>you will not be considered without below mentioned documents</span>)<br><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp;1.Acceptance letter from the college<br>&nbsp;&nbsp;&nbsp;&nbsp;2.Mail confirmation<br><br>"
						+ "<span style='background-color:rgb(255,229,153)'><b>Workshop dates : 17th to 26th October 2016 from 10:00 am to 06:00 pm</b></span><br><br>"
						+ "<span style='color:#0000ff;'>Certificate:</span> Provided by IBM & APSSDC ( Attendance & work submission during 10 days is mandatory for certificate issue)<br><br>"
						+ "<span style='color:#0000ff;'>Accommodation:</span> <span style='background-color:rgb(255,229,153)'>Food and Accommodation will be provided at the venue(Paid basis).</span><br><br>"
						+ "<span style='color:#0000ff;'>Dress Code:</span> Only formals (IBM team is going to shoot the sessions and interact with Faculty members.)<br><br>"
						+ "For more details please contact the respective APSSDC Team members as given below:<br><br>"
						+ "<table border=1>" + "<tr style='background-color:rgb(169,208,142);'>"
						+ "<th>SNo</th>" + "<th>District</th>" + "<th>Co-ordinator</th>"
						+ "<th>Email</th>" + "<th>Mobile</th>" + "</tr>"
						+ "<tr style='background-color:rgb(198,224,180);'>" + "<td>1</td>"
						+ "<td>Srikakulam</td>" + "<td rowspan='2'>N Rajesh</td>"
						+ "<td>pm.skl@apssdc.in</td>" + "<td rowspan='2'>9100965113</td>" + "</tr>"
						+ "<tr style='background-color:rgb(198,224,180);'>" + "<td>2</td>"
						+ "<td>Vizianagaram</td>" + "<td>pm.vzm@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(255,230,153);'>" + "<td>3</td>"
						+ "<td>Vishakapatnam</td>" + "<td rowspan='2'>V V S Priyanka</td>"
						+ "<td>pm.vsp@apssdc.in</td>" + "<td rowspan='2'>9492413589</td>" + "</tr>"
						+ "<tr style='background-color:rgb(255,230,153);'>" + "<td>4</td>"
						+ "<td>East Godavari</td>" + "<td>pm.eg@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(219,219,219);'>" + "<td>5</td>"
						+ "<td>West Godavari</td>" + "<td rowspan='2'>Ch Venkatesh</td>"
						+ "<td>pm.wg@apssdc.in</td>" + "<td rowspan='2'>9492413589</td>" + "</tr>"
						+ "<tr style='background-color:rgb(219,219,219);'>" + "<td>6</td>"
						+ "<td>Krishna</td>" + "<td>pm.kri@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(189,215,238);'>" + "<td>7</td>"
						+ "<td>Guntur</td>" + "<td rowspan='2'>D Vamsi Krishna</td>"
						+ "<td>pm.gtr@apssdc.in</td>" + "<td rowspan='2'>9100965104</td>" + "</tr>"
						+ "<tr style='background-color:rgb(189,215,238);'>" + "<td>8</td>"
						+ "<td>Prakasam</td>" + "<td>pm.pks@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(248,203,173);'>" + "<td>9</td>"
						+ "<td>Nellore</td>" + "<td rowspan='2'>P Divya</td>"
						+ "<td>pm.nlr@apssdc.in</td>" + "<td rowspan='2'>8500558723</td>" + "</tr>"
						+ "<tr style='background-color:rgb(248,203,173);'>" + "<td>10</td>"
						+ "<td>Chittoor</td>" + "<td>pm.ctr@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(198,224,180);'>" + "<td>11</td>"
						+ "<td>Cuddapah</td>" + "<td rowspan='2'>D Vamsi Krishna</td>"
						+ "<td>pm.kdp@apssdc.in</td>" + "<td rowspan='2'>9100965102</td>" + "</tr>"
						+ "<tr style='background-color:rgb(198,224,180);'>" + "<td>12</td>"
						+ "<td>Ananthapur</td>" + "<td>pm.atp@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(172,185,202);'>" + "<td>13</td>"
						+ "<td>Kurnool</td>" + "<td>P Manasa</td>" + "<td>pm.knl@apssdc.in</td>"
						+ "<td>9059498813</td>" + "</tr>" + "</table> <br><br>"
						+ "Please find the below attached documents for course content"

						+ "" + "&nbsp;";

		String msg1 =
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram()
						+ "</b> at the <b>Skill Development Centre(SDC), RVR & JC College of Engineering(Autonomous).</b><br><br>"
						+ "It is the view of APSSDC  that Pre-training is necessary during the interactions with EDS Technologies team. APSSDC would like to conduct Pre-training program for Faculty to make Faculty ready for EDS Technology team training with good hands on experience on prerequisites.<br><br>"
						+ "We request the colleges to make registered faculty available for Pre-training during morning session (from 9:30 to 12:30) through GoToMeeting online sessions from <span style='background-color:rgb(169,208,142);'>18th to 21st Oct 2016 </span>. GoToMeeting id & link will be communicated later.<br><br>"
						+ "<span style='background-color:rgb(169,208,142);'>Note :  Pre-Training is mandatory for attending Revit Structure (CIVIL) (24th to 28th Oct 2016 at RVR & JC College of Engineering Guntur). Faculty who attend the Pre-training  & submit the work in time  will be considered for Faculty Improvement Program - Revit Structure. </span>"
						+ "Faculty members attending the Pre-training  have to be ready with their Laptop by loading the following software's. <br><br>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp;1.Auto CAD (latest version)<br>&nbsp;&nbsp;&nbsp;&nbsp;2.Internet<br>&nbsp;&nbsp;&nbsp;&nbsp;3.Laptop Configuration( 8 GB RAM recommended)<br><br>"
						+ "For more details please contact the respective APSSDC Team members as given below:<br><br>"
						+ "<table border=1>" + "<tr style='background-color:rgb(169,208,142);'>"
						+ "<th>SNo</th>" + "<th>District</th>" + "<th>Co-ordinator</th>"
						+ "<th>Email</th>" + "<th>Mobile</th>" + "</tr>"
						+ "<tr style='background-color:rgb(198,224,180);'>" + "<td>1</td>"
						+ "<td>Srikakulam</td>" + "<td rowspan='2'>N Rajesh</td>"
						+ "<td>pm.skl@apssdc.in</td>" + "<td rowspan='2'>9100965113</td>" + "</tr>"
						+ "<tr style='background-color:rgb(198,224,180);'>" + "<td>2</td>"
						+ "<td>Vizianagaram</td>" + "<td>pm.vzm@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(255,230,153);'>" + "<td>3</td>"
						+ "<td>Vishakapatnam</td>" + "<td rowspan='2'>V V S Priyanka</td>"
						+ "<td>pm.vsp@apssdc.in</td>" + "<td rowspan='2'>9492413589</td>" + "</tr>"
						+ "<tr style='background-color:rgb(255,230,153);'>" + "<td>4</td>"
						+ "<td>East Godavari</td>" + "<td>pm.eg@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(219,219,219);'>" + "<td>5</td>"
						+ "<td>West Godavari</td>" + "<td rowspan='2'>Ch Venkatesh</td>"
						+ "<td>pm.wg@apssdc.in</td>" + "<td rowspan='2'>9492413589</td>" + "</tr>"
						+ "<tr style='background-color:rgb(219,219,219);'>" + "<td>6</td>"
						+ "<td>Krishna</td>" + "<td>pm.kri@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(189,215,238);'>" + "<td>7</td>"
						+ "<td>Guntur</td>" + "<td rowspan='2'>D Vamsi Krishna</td>"
						+ "<td>pm.gtr@apssdc.in</td>" + "<td rowspan='2'>9100965104</td>" + "</tr>"
						+ "<tr style='background-color:rgb(189,215,238);'>" + "<td>8</td>"
						+ "<td>Prakasam</td>" + "<td>pm.pks@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(248,203,173);'>" + "<td>9</td>"
						+ "<td>Nellore</td>" + "<td rowspan='2'>P Divya</td>"
						+ "<td>pm.nlr@apssdc.in</td>" + "<td rowspan='2'>8500558723</td>" + "</tr>"
						+ "<tr style='background-color:rgb(248,203,173);'>" + "<td>10</td>"
						+ "<td>Chittoor</td>" + "<td>pm.ctr@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(198,224,180);'>" + "<td>11</td>"
						+ "<td>Cuddapah</td>" + "<td rowspan='2'>D Vamsi Krishna</td>"
						+ "<td>pm.kdp@apssdc.in</td>" + "<td rowspan='2'>9100965102</td>" + "</tr>"
						+ "<tr style='background-color:rgb(198,224,180);'>" + "<td>12</td>"
						+ "<td>Ananthapur</td>" + "<td>pm.atp@apssdc.in</td>" + "</tr>"
						+ "<tr style='background-color:rgb(172,185,202);'>" + "<td>13</td>"
						+ "<td>Kurnool</td>" + "<td>P Manasa</td>" + "<td>pm.knl@apssdc.in</td>"
						+ "<td>9059498813</td>" + "</tr>" + "</table> <br><br>"

						+ "" + "&nbsp;";

		String msg2 =
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b>.<br><br>"
						+ "Further details we will intimate later";
		
		String msg3 =
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b>.<br><br>"
						+ "<span style='color:#0000ff;'>Pre-Training(Online) Dates:</span> 2 days (20th & 21st Oct 2016) <br><br>"
						+ "<span style='color:#0000ff;'>Assessment Test:</span> 21st Oct 2016(tentative)<br><br>"
						+ "<span style='background-color:rgb(169,208,142);'>APSSDC will sponsor the faculty & interns after attending the Pre-training program online from 20th - 21st October . Based on the attendance & Assessment , faculty members and interns will be shortlisted for the National Seminar sponsorship and will be considered for future FIP programs of APSSDC on IOT. </span><br><br>"
						+ "<span style='color:#0000ff;'>Fee sponsorship details:</span <br>&nbsp;&nbsp;&nbsp;&nbsp;1. Research Scholar: Rs.1, 725 /-<br>&nbsp;&nbsp;&nbsp;&nbsp;2. Student: Rs.1, 725 /-<br>&nbsp;&nbsp;&nbsp;&nbsp;3. Government/ Private organization <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a. IE members : Rs.2, 300 /-<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b. Non IE members : Rs.3, 450/-<br><br>"
						+ "<span style='color:#0000ff;'>Fee:</span> Sponsored by APSSDC<br><br>"
						+ "<span style='color:#0000ff;'>Accommodation:</span> Faculty & Interns have to make their own arrangements<br><br>"
						+ "For more details about National seminar: http://www.ieivjlc.org/<br><br>"
						+ "<b>Please find the attached documents on</b><br>&nbsp;&nbsp;&nbsp;&nbsp;1. National seminar Brochure<br>&nbsp;&nbsp;&nbsp;&nbsp;2. APSSDC Internship students details<br>&nbsp;&nbsp;&nbsp;&nbsp;3. Pre-Training content";

		String msg4 =
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the Faculty Improvement Program on <b>"
						+ registration.getProgram() + "</b> "
						+ "at the APSSDC COE Centre, Vasireddy Venkatadri Institute of Technology (VVIT) Guntur. <br><br>"
						+ "It is the view of APSSDC  that Pre-training is necessary during the interactions with Google Team. APSSDC would like to conduct Pre-training program for Faculty to make Faculty ready for Google team training with good hands on experience on prerequisites . The APSSDC Pre-training team is trained by Google team in Delhi during June 2016.<br><br>"
						+ "We request the colleges to make registered faculty available for Pre-training during morning session (from 9:30 to 12:30) through GoToMeeting online sessions from <b>3rd to 5th Nov 2016.</b>  <br><br>"
						+ "<b>Note :  Pre-Training is mandatory for attending this program (07th to 11th Nov 2016 at APSSDC COE Centre, Vasireddy Venkatadri Institute of Technology Guntur). Faculty who attend the Pre-training  & submit the work in time  will be considered for Google workshop. </b><br><br>"
						+ "Faculty members attending the Pre-training have to be ready with their Laptop by loading the following software's. <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Java 1.7 and above(Versions)<br>&nbsp;&nbsp;&nbsp;&nbsp;2. Android Studio 2.1 and above Versions<br>&nbsp;&nbsp;&nbsp;&nbsp;3. High speed Internet<br>&nbsp;&nbsp;&nbsp;&nbsp;4. Laptop Configuration (8 GB RAM recommended)<br><br>"
						+ "Google team is expecting the colleges to propagate Android skills to at least 60 students from 3rd year and 60 students from 4th year with the help of Google, APSSDC & Faculty who got trained through this program. The faculty members participating in the program have to propagate the skills to the students by conducting Android Developers Fundamentals program to their students with the help of APSSDC.<br><br>"
						+ "Student registration link : <a href='http://202.65.133.69:8181/SIP/'>http://202.65.133.69:8181/SIP/</a><br><br>"
						+ "Online meeting Id will be provided on <b>2nd Nov 2016.</b>";
		String msg5=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at the Skill Development Centre (SDC), SRKR Engineering College Bhimavaram. <br><br>"
						+"<b>Course Overview Online Pre-Training :</b>" +" starts from 14th to 16th Nov  2016 , from 09:30 am to 12:30 pm (3 hrs).<br><br>"
						+ "IBM team is expecting the colleges to propagate Business Analytics course to at least 30 students from 3rd year and 30 students from 4th year with the help of IBM, APSSDC & Faculty who got trained through this program. The faculty members participating in the program have to propagate the skills to the students by conducting Business Analytics to their students with the help of IBM .<br><br>"
						+ "<b>Fee structure for students :</b> Rs.7,000/- (Rs. 5,000/- for Course fee + Rs. 2,000/- for IBM Certification), Free for SC/ST students. <br><br>"
						+ "<b>Student registration link :</b> <a href='http://202.65.133.69:8181/SIP/'>http://202.65.133.69:8181/SIP/</a><br><br>"
						+ "<b>Note :  Pre-Training is mandatory for attending this program (21st to 26th Nov 2016 at Skill Development Centre (SDC), SRKR Engineering College Bhimavaram). Faculty who attend the Pre-training,submit the work & students list will be considered for Business Analytics.   </b><br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. FIP - Course content & Pre Training Schedule<br>&nbsp;&nbsp;&nbsp;&nbsp;2. FIP - Class room training Schedule<br>&nbsp;&nbsp;&nbsp;&nbsp;3. SIP - Business Analytics software for Academia<br>&nbsp;&nbsp;&nbsp;&nbsp;4. SIP - Flyer<br>&nbsp;&nbsp;&nbsp;&nbsp;5. SIP - Software/Hardware Requirements<br>&nbsp;&nbsp;&nbsp;&nbsp;6. iCAT  Track  Course Outline<br><br>";
		String msg6=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at the Skill Development Centre (SDC),Madanepalle Institute of Technology & Science (MITS) Chittoor. <br><br>"
						+ "<b>Note :   Pre-Training is mandatory for attending this program (28th Nov to 03rd Dec 2016 at Madanepalle Institute of Technology & Science (MITS) Chittoor). Faculty who attend the Pre-training  & submit the work in time  will be considered for this program.  </b><br><br>"
						+"<b>Course Overview Online Pre-Training :</b>" +" starts from 22nd to 25th Nov  2016 , from 09:30 am to 12:30 pm (3 hours).<br><br>"
						+ "<b>Certificate :</b> Participation certificate will be provided by EDS Technologies & APSSDC. <br><br>"
						+ "<b>FIP Dates :</b> 28th Nov to 03rd Dec 2016 from 9:00 AM to 6:00 PM.<br><br>"
						+ "<b>Fee:</b> Nil. <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ "<b>For Gents :</b> Participants are required to make their own arrangements for stay, Lunch will be provided at the Venue. <br>"
						+ "<b>For Ladies :</b>  Food and Accommodation will be provided at the Madanepalle Institute of Technology & Science  per day Rs.200/- for day. Transportation is provided from Madanepalle. <br> <br>"
						+ "<b>Venue :</b> Skill Development Centre (SDC), Madanepalle Institute of Technology & Science. <br><br>"
						+ "<b>Address :</b>  Kadiri Road, Angallu Village, Chittoor District, Madanapalle, Andhra Pradesh 517325. <br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Day wise Schedule<br>&nbsp;&nbsp;&nbsp;&nbsp;2. Course content for students<br>&nbsp;&nbsp;&nbsp;&nbsp;3. Resource person profile<br><br>"; 
		String msg7=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at the Skill Development Centre (SDC),Godavari Institute of Engineering & Technology Rajahmundry. <br><br>"
						+ "<b>Note :   Pre-Training is mandatory for attending this program (29th Nov to 4th Dec 2016 at Godavari Institute of Engineering & Technology Rajahmundry). Faculty who attend the Pre-training  & submit the work in time  will be considered for this program.  </b><br><br>"
						+"<b>Course Overview Online Pre-Training :</b>" +"starts from 24th to 26th Nov  2016 , from 10:00 am to 12:30 pm (2 : 30 hours).<br><br>"
						+ "<b>Certificate :</b> Participation certificate will be provided by CoreEL & APSSDC. <br><br>"
						+ "<b>Timings :</b>  9:00 AM to 6:00 PM - 29th Nov to 04th Dec 2016.<br><br>"
						+ "<b>Fee:</b> Nil. <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ "Food and Accommodation will be provided at the Godavari Institute of Engineering & Technology  per day Rs.200/- for day. Transportation is provided from Rajahmundry. <br><br>"
						+ "<b>Venue :</b> Skill Development Centre (SDC), Godavari Institute of Engineering & Technology Rajahmundry.<br><br>"
						+ "<b>Address :</b>NH-16, Chaitanya Knowledge City, Rajahmundry, Andhra Pradesh 533296. <br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Course content<br>&nbsp;&nbsp;&nbsp;&nbsp;2. Resource person profile<br><br>"; 
		String msg8=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at the Skill Development Centre (SDC), NBKR Institute of Engineering & Technology. <br><br>"
						+ "<b>Note :   Pre-Training is mandatory for attending this program (10th to 15th April 2017 at NBKR Institute of Engineering & Technology). Faculty who attend the Pre-training  & submit the work in time  will be considered for this program.  </b><br><br>"
						+"<b>Please note that FIP attended faculty will get CATIA licenses from APSSDC.</b><br><br>"
						+ "APSSDC is expecting the colleges to propagate CATIA course to at least 30 students from 3rd year and 30 students from 4th year with the help of EDS Technologies , APSSDC & Faculty who got trained through this program. The faculty members participating in the program have to propagate the skills to the students by conducting CATIA course in this Summer - 2017 to their students with the help of APSSDC.<br><br>"
						+"<b>Course Overview Online Pre-Training :</b>" +" Starts from 03rd to 06th April  2017 , from 09:30 AM to 12:30 PM (3 hrs).<br><br>"
						+ "<b>Certificate :</b> Participation certificate will be provided by APSSDC. <br><br>"
						+ "<b>Timings :</b>  9:00 AM to 6:00 PM - 10th to 15th April 2017.<br><br>"
						+ "<b>Fee:</b> Nil. <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ "Food and Accommodation will be provided at the NBKR Institute of Engineering & Technology per day Rs.200/- per day.<br><br>"
						+ "<b>Venue :</b> Skill Development Centre (SDC), NBKR Institute of Engineering & Technology.<br><br>"
						+ "<b>Address :</b>Vidyanagar, Andhra Pradesh 524413. <br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Pre Training Course Content<br>&nbsp;&nbsp;&nbsp;&nbsp;2. Class room training Day wise Course Content.<br>&nbsp;&nbsp;&nbsp;&nbsp;3. Resource person Profile<br>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>"
		                +"If you have any queries drop a mail to:&nbsp;"+"kynvenkat@gmail.com"+"<br><br>";
		
		String msg9=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at the Skill Development Centre (SDC), QIS College of Engineering and Technology. <br><br>"
						+ "<b>Note :   Pre-Training is mandatory for attending this program (17th to 22nd April 2017 at QIS College of Engineering and Technology). Faculty who attend the Pre-training  & submit the work in time  will be considered for this program.  </b><br><br>"
						+"<b>Course Overview Online Pre-Training :</b>" +" Starts from 10th to 12th April  2017 , from 09:30 AM to 12:30 PM (3 hrs).<br><br>"
						+ "<b>Certificate :</b> Participation certificate will be provided by APSSDC. <br><br>"
						+ "<b>Timings :</b>  9:00 AM to 6:00 PM - 17th to 22nd April 2017.<br><br>"
						+ "<b>Fee:</b> Nil. <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ "Food and Accommodation will be provided at the  QIS College of Engineering and Technology  Rs.200/- per day.<br><br>"
						+ "<b>Venue :</b> Skill Development Centre (SDC), QIS College of Engineering and Technology.<br><br>"
						+ "<b>Address :</b>Ongole, Vegamukkapalem, Andhra Pradesh 523272. <br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Pre Training Course Content<br>&nbsp;&nbsp;&nbsp;&nbsp;2. Class room training Day wise Course Content.<br>&nbsp;&nbsp;&nbsp;&nbsp;3. Resource person Profile<br>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>"
		                +"<b>For more information about this program,please contact Bathena Alekya (9553371077) ; Email: alekya.b@apssdc.in; <br><br>";
		
		String msg10=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at Fortune Murali Park, Vijayawada. <br><br>"
						+"<b>Branch Eligibility :</b>" +" Circuit & Non Circuit branches (ECE/CSE/IT/EEE/Mechanical/Civil).<br><br>"
						+ "<b>Workshop Dates :</b> 5th to 6th May 2017 <br><br>"
						+ "<b>Fee:</b> Rs. 3,333/- per candidate (Sponsored by APSSDC). <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ " Faculty, UIF (University Innovation Fellows) Candidates & Students have to make their own .<br><br>"
						+ "<b>Venue :</b> Fortune Murali Park 40-1-28, MG Road, Labbipet, Beside DV Manor, Vijayawada, Andhra Pradesh – 520010.<br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Smart Cities Symposium 2017<br><br>"
		                +"<b>For more information about this program,please contact Boyapati Alekhya (9177033668) ; Email: alekhya.b@apssdc.in; <br><br>";
		
		String msg11=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b><br><br> "
						+ "<b>Training Dates :</b> 15th to 27th May 2017 <br><br>"
						+ "<b>Timings: </b>9:00 AM to 12:00 PM.  <br><br>"
						+ "<b>Training Mode :</b>Online.  <br><br>"
						+ "<b>Course Fee :</b> Nil. <br><br>"
						+ "<b>GoToMeeting link :</b> <a href='https://global.gotomeeting.com/join/898561253'>https://global.gotomeeting.com/join/898561253</a><br><br>"
						 +"<b>For more information about this program,please contact P.Divya (9912597135) ; Email: divya.p@apssdc.in; </b><br><br>"
						+ "Please find the following attachments.<br><br>"
		                +"&nbsp;&nbsp;&nbsp;&nbsp;1. Course Content<br><br>";
		
		String msg13=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at the APSSDC COE Centre, Vasireddy Venkatadri Institute of Technology (VVIT) Guntur. <br><br>"
						+ "<b>Certificate :</b> Participation certificate will be provided by SIEMENS & APSSDC. <br><br>"
						+ "<b>Timings :</b>  9:00 AM to 6:00 PM - 15th to 20th May 2017.<br><br>"
						+ "<b>Fee:</b> Nil. <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ "Participants are required to make their own arrangements for stay. Transportation is provided from Vijayawada, Guntur.  Lunch will be provided at the Venue.<br><br>"
						+ "<b>Venue :</b> APSSDC SIEMENS CENTER OF EXCELLENCE  - Vasireddy Venkatadri Institute of Technology.<br><br>"
						+ "<b>Address :</b>Gollamudi Road, Nambur, Guntur Dt., Andhra Pradesh - 522508. <br><br>"
						+"<b>For more information about this program,please contact Boyapati Alekhya (9177033668) ; Email: alekhya.b@apssdc.in; <br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Course Content<br>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>";
		                
		String msg14=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at the APSSDC COE Centre, Vasireddy Venkatadri Institute of Technology (VVIT) Guntur. <br><br>"
						+ "<b>Certificate :</b> Participation certificate will be provided by SIEMENS & APSSDC. <br><br>"
						+ "<b>Timings :</b>  9:00 AM to 6:00 PM - 15th to 20th May 2017.<br><br>"
						+ "<b>Fee:</b> Nil. <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ "Participants are required to make their own arrangements for stay. Transportation is provided from Vijayawada, Guntur.  Lunch will be provided at the Venue.<br><br>"
						+ "<b>Venue :</b> APSSDC SIEMENS CENTER OF EXCELLENCE  - Vasireddy Venkatadri Institute of Technology.<br><br>"
						+ "<b>Address :</b>Gollamudi Road, Nambur, Guntur Dt., Andhra Pradesh - 522508. <br><br>"
						+"<b>For more information about this program,please contact Boyapati Alekhya (9177033668) ; Email: alekhya.b@apssdc.in; <br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Course Content<br>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>";
		String msg15=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> "
						+ "at the APSSDC COE Centre, Vasireddy Venkatadri Institute of Technology (VVIT) Guntur. <br><br>"
						+ "<b>Certificate :</b> Participation certificate will be provided by SIEMENS & APSSDC. <br><br>"
						+ "<b>Timings :</b>  9:00 AM to 6:00 PM - 15th to 20th May 2017.<br><br>"
						+ "<b>Fee:</b> Nil. <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ "Participants are required to make their own arrangements for stay. Transportation is provided from Vijayawada, Guntur.  Lunch will be provided at the Venue.<br><br>"
						+ "<b>Venue :</b> APSSDC SIEMENS CENTER OF EXCELLENCE  - Vasireddy Venkatadri Institute of Technology.<br><br>"
						+ "<b>Address :</b>Gollamudi Road, Nambur, Guntur Dt., Andhra Pradesh - 522508. <br><br>"
						+"<b>For more information about this program,please contact Boyapati Alekhya (9177033668) ; Email: alekhya.b@apssdc.in; <br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Course Content<br>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>";
		String msg17=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "We are happy to inform that you have successfully completed registration for the <b>"
						+ registration.getProgram() + "</b> <br><br>"
						+ "<b>Timings :</b>  9:00 AM to 6:00 PM - 22nd to 23rd May 2017.<br><br>"
						+ "<b>Fee:</b> Nil. <br><br>"
						+ "<b>Accommodation:</b> <br><br>"
						+ "Registered members have to make their own arrangements for food and accommodation.<br><br>"
						+ "<b>Venue :</b> TATA Consultancy Services,Think Campus, Plot No. 42P & 45P, Electronic City Phase II.<br><br>"
						+"<b>Landmark:</b> Near Mahindra Satyam Co, Hosur Road, Electronic City, Bengaluru, Karnataka - 560100.<br><br>" 
						+"<b>For more information about this program,please contact Venkat (9912137965) ; Email: kynvenkat@gmail.com; </b><br><br>"
						+ "We look forward to organise more such specialized workshops in future! <br><br>"
						+ "Please find the following attachments <br><br>"
						+"&nbsp;&nbsp;&nbsp;&nbsp;1. Course Content<br>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>";
		String msg18=
				"Dear&nbsp;"
						+ registration.getName()
						+ ",<br>"
						+ "<br> Greetings from APSSDC!<br><br>"
						+ "Thank you for registration. Please click the bellow link to give&nbsp;confirmation for&nbsp;AWS Skill Guru by selecting the cluster"
						+"<br><a href='https://goo.gl/forms/OpXle86eDbTuIPCi1'>https://goo.gl/forms/OpXle86eDbTuIPCi1</a>";
				
		if (registration.getProgramId() == 3)
		{
			email.setText(msg);
		}
		else if (registration.getProgramId() == 4)
		{
			email.setAttachments(null);
			email.setText(msg4);
		}
		else if (registration.getProgramId() == 61)
		{
			email.setAttachments(null);
			email.setText(msg18);
		}
		else if (registration.getProgramId() == 9)
		{
			email.setText(msg3);
		}
		else if (registration.getProgramId() == 10)
		{
			email.setText(msg5);
		}
		else if (registration.getProgramId() == 11)
		{
			email.setText(msg8);
		}
		else if (registration.getProgramId() == 12)
		{
			email.setText(msg9);
		}
		else if (registration.getProgramId() == 8)
		{
			email.setText(msg7);
		}
		else if (registration.getProgramId() == 6)
		{
			email.setText(msg6);
		}
		else if (registration.getProgramId() == 16)
		{
			email.setText(msg10);
		}
		else if (registration.getProgramId() == 18)
		{
			email.setText(msg11);
		}
		else if (registration.getProgramId() == 13)
		{
			email.setText(msg13);
		}
		else if (registration.getProgramId() == 14)
		{
			email.setText(msg14);
		}
		else if (registration.getProgramId() == 15)
		{
			email.setText(msg15);
		}
		else if (registration.getProgramId() == 17)
		{
			email.setText(msg17);
		}
		else
		{
			email.setAttachments(null);
			email.setText(msg2);
		}
		sendHtmlMsg(email);
		System.out.println("success");
		this.response.setSuccessful(true);
		this.response.setResponseObject(registration);
		return this.response;
	}
		*/
}
