package com.foodordering.service.impl;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.foodordering.entity.GroupOrder;
import com.foodordering.service.EmailService;
import com.foodordering.service.OrderService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private OrderService orderService;

	@Override
	public void sendMail(GroupOrder groupOrder) {
		Context context = new Context();
		context.setVariable("groupOrder", groupOrder);

		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(groupOrder.getRestaurant().getEmail());
			messageHelper.setSubject("Food ordering");
			messageHelper.setText(templateEngine.process("email-template", context), true);
		};

		mailSender.send(messagePreparator);
	}

	@Override
	public void emailTimer(long delay, GroupOrder groupOrder) {
		System.out.println("Calling method emailTimer. Current time: " + new Date());
		Timer timer = new Timer("email", true);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				groupOrder.setOrders(orderService.getAllOrdersForGroupOrder(groupOrder));
				if (groupOrder.getOrders().size() != 0) {
					Context context = new Context();
					context.setVariable("groupOrder", groupOrder);

					MimeMessagePreparator messagePreparator = mimeMessage -> {
						MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
						messageHelper.setTo(groupOrder.getRestaurant().getEmail());
						messageHelper.setSubject("Food ordering");
						messageHelper.setText(templateEngine.process("email-template", context), true);
					};

					mailSender.send(messagePreparator);
				}
			}
		}, delay);
	}
}
