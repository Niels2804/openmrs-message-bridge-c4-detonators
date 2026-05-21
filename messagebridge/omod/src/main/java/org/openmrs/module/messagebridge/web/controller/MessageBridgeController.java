// package org.openmrs.module.messagebridge.web.controller;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.beans.factory.annotation.Autowired;

// import org.openmrs.module.messagebridge.service.MessageBridgeService;

// @Controller
// @RequestMapping("/module/messagebridge")
// public class MessageBridgeController {

// 	@Autowired
// 	private MessageBridgeService service;

// 	@RequestMapping(value = "/sync")
// 	@ResponseBody
// 	public String sync() {
// 		service.syncAppointments();
// 		return "SYNC STARTED";
// 	}
// }
