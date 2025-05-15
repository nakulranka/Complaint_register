<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Complaint Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        form {
            max-width: 400px;
        }
        label {
            display: block;
            margin-top: 10px;
        }
        input, textarea, button {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
        }
        button {
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <h2>Submit Complaint</h2>
    <form action="submit" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required />

        <label for="message">Message:</label>
        <textarea id="message" name="message" rows="4" required></textarea>

        <button type="submit">Submit</button>
    </form>
</body>
</html>
