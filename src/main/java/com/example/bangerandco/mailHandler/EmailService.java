package com.example.bangerandco.mailHandler;

import com.example.bangerandco.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    SimpleMailMessage message = new SimpleMailMessage();

    public void registerUser(String email) {
        message.setFrom("eirlssbangerandco@gmail.com");
        message.setTo(email);
        message.setSubject("Welcome to BangerAndCo!");
        message.setText("Thank You for joining BangerAndCo for your travelling needs!\n" +
                "You will be able to place booking with us as soon as an admin verifies you. \n" +
                "Happy travelling :)");

        mailSender.send(message);
    }

    public void verifyUser(User user) {
        message.setFrom("eirlssbangerandco@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Verified on BangerAndCo!");
        message.setText("You have been verified by the admins of Banger!\n" +
                "You are now able to make bookings\n" +
                "Happy travelling :)");

        mailSender.send(message);
    }

    public void blacklistUser(User user) {
        message.setFrom("eirlssbangerandco@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Blacklisted on BangerAndCo!");
        message.setText("You have been blacklisted by the admins of Banger!\n" +
                "You are now restricted from services of BangerAndCo");

        mailSender.send(message);
    }

    public void whitelistUser(User user) {
        message.setFrom("eirlssbangerandco@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Verified on BangerAndCo!");
        message.setText("You have been whitelisted by the admins of Banger!\n" +
                "You are now able to make bookings\n" +
                "Happy travelling :)");

        mailSender.send(message);
    }

    public void createBooking(User user) {
        message.setFrom("eirlssbangerandco@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Booking successful!");
        message.setText("Your booking is successfully added!");

        mailSender.send(message);
    }

    public void changeStatus(String status, User user) {
        message.setFrom("eirlssbangerandco@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Booking updated!");
        message.setText("Your booking has been" + status + "!");

        mailSender.send(message);
    }

    public void cancelBooking(String email) {
        message.setFrom("eirlssbangerandco@gmail.com");
        message.setTo(email);
        message.setSubject("Booking cancelled!");
        message.setText("Your booking is cancelled successfully!");

        mailSender.send(message);
    }

    public void updateBooking(String email) {
        message.setFrom("eirlssbangerandco@gmail.com");
        message.setTo(email);
        message.setSubject("Booking Updated!");
        message.setText("Your booking is updated successfully!");

        mailSender.send(message);
    }
}
