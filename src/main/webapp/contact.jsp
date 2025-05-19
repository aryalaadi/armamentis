<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Us</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/contact.css">
</head>
<body>
<div class="contact-container">
    <h2 class="contact-title">Contact Us</h2>

    <form class="contact-form" action="#" method="post">
        <label>Name</label>
        <input type="text" name="name" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>Subject</label>
        <input type="text" name="subject" required>

        <label>Message</label>
        <textarea name="message" rows="5" required></textarea>

        <button type="submit">Send Message</button>
    </form>

    <div class="home-link">
        <a href="dashboard.jsp">Return to Home</a>
    </div>
</div>
</body>
</html>
