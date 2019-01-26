package com.foodordering.service.impl;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

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
