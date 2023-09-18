package com.moyeo.controller;

import java.io.IOException;
import java.util.HashMap; 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moyeo.dto.Notice;
import com.moyeo.dto.Pack;
import com.moyeo.dto.Userinfo;
import com.moyeo.exception.UserinfoNotFoundException;
import com.moyeo.service.DiyService;
import com.moyeo.service.NoticeService;
import com.moyeo.service.PackageHeartService;
import com.moyeo.service.PackageService;
import com.moyeo.service.QaService;
import com.moyeo.service.ReviewService;
import com.moyeo.service.UserinfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
	private final UserinfoService userinfoService;
	private final PackageService packageService;
	private final QaService qaService;
	private final DiyService diyService;
	private final ReviewService reviewService;
	private final PackageHeartService packageHeartService;
	private final NoticeService noticeService;
	
	@GetMapping("/userinfo-list")
	public Map<String, Object> getUserinfos(@RequestParam(defaultValue = "1") int pageNum, 
											@RequestParam(defaultValue = "20") int pageSize, 
											@RequestParam(defaultValue = "") String selectKeyword) {
		return userinfoService.getUserinfoList(pageNum, pageSize, selectKeyword);
	}
	
	@GetMapping("/userinfo-details")
	public Map<String, Object> getMyAccounts(@RequestParam String id,
											 @RequestParam int diyPageNum,
											 @RequestParam int qaPageNum,
											 @RequestParam int reviewPageNum,
											 @RequestParam int packageHeartPageNum) {
		
		Map<String, Object> myDiy;
		myDiy = diyService.getMyDiyList(diyPageNum, id);
		Map<String, Object> myQa;
		myQa = qaService.getMyQaList(qaPageNum, id);
		Map<String, Object> myReview;
		myReview = reviewService.getMyReviewList(reviewPageNum, id);
		Map<String, Object> myPackHeart;
		myPackHeart = packageHeartService.getMyPackageHeartList(packageHeartPageNum, id);
		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("myDiy", myDiy);
		myMap.put("myQa", myQa);
		myMap.put("myReview", myReview);
		myMap.put("myPackHeart", myPackHeart);
		return myMap;
	}
	
	@PutMapping("/userinfo-modify")
	public String modifyUserinfo(@RequestBody Userinfo userinfo) throws UserinfoNotFoundException {
		userinfo.setName(userinfo.getName());
		userinfo.setEmail(userinfo.getEmail());
		userinfo.setPhone(userinfo.getPhone());
		userinfo.setAddress(userinfo.getAddress());
		userinfo.setStatus(userinfo.getStatus());
		userinfoService.modifyUserinfoByAdmin(userinfo);
		return "success";
	}
	
	@DeleteMapping("/userinfo-remove")
	public String removeAccount(@RequestParam String id) throws UserinfoNotFoundException {
		userinfoService.removeUserinfo1(id);
		return "success";
	}
	
	/*
	@GetMapping("/selected_list")
	public Map<String, Object> getSelectPackageList() {
		List<Pack> deadlinePackage = packageService.getDeadlinePackage();
		List<Pack> bestPackageByHeart = packageService.getBestPackageByHeart();

		Map<String, Object> pageMap = new HashMap<String, Object>();

		pageMap.put("deadlinePackage", deadlinePackage);
		pageMap.put("bestPackageByHeart", bestPackageByHeart);

		return pageMap;
	}
	*/
	
	@GetMapping("/package-list")
	public Map<String, Object> getPackages(@RequestParam(defaultValue = "1") int pageNum,
										   @RequestParam(defaultValue = "20") int pageSize,
										   @RequestParam(defaultValue = "") String selectKeyword) {
		return packageService.getPackageList(pageNum, pageSize, selectKeyword);
	}
	
	@PutMapping("/package-accept")
	public String acceptPackage(@RequestBody Pack pack, HttpSession httpSession) {
		pack.setPackStatus(0);
		packageService.modifyPackageStatus(pack);

		return "success";
	}
	
	@PutMapping("/package-reject")
	public String rejectPackage(@RequestBody Pack pack, HttpSession httpSession) {
		pack.setPackStatus(1);
		packageService.modifyPackageStatus(pack);

		return "success";
	}

	
	
	@GetMapping("/diy-list")
	public Map<String, Object> getDiys(@RequestParam(defaultValue = "1") int pageNum,
									   @RequestParam(defaultValue = "20") int pageSize,
									   @RequestParam(defaultValue = "") String selectKeyword) {
		return diyService.getDiyList(pageNum, pageSize, selectKeyword);
	}
	
	@GetMapping("/notice-list")
	public Map<String, Object> getNotices(@RequestParam(defaultValue = "1") int pageNum,
										  @RequestParam(defaultValue = "20") int pageSize,
										  @RequestParam(defaultValue = "") String selectKeyword) {
		return noticeService.getNoticeList(pageNum, pageSize, selectKeyword);
	}
	
	
	@PostMapping("/notice_add")
	public String addNotice(@RequestBody Notice notice, HttpServletRequest request)
			throws IllegalStateException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		notice.setNoticeTitle(title);
		notice.setNoticeContent(content);
		noticeService.insertNotice(notice);

		// 나머지 공지사항 등록 로직을 수행합니다.
		return "success";
	}
	
	
	
	@GetMapping("/qa-list")
	public Map<String, Object> getQas(@RequestParam(defaultValue = "1") int pageNum,
									  @RequestParam(defaultValue = "20") int pageSize,
									  @RequestParam(defaultValue = "") String selectKeyword) {
		return qaService.getQaList1(pageNum, pageSize, selectKeyword);
	}
}	
