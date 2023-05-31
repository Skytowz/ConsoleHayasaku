package com.hayasaku.console.controller.servlet;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hayasaku.console.model.dao.TagCategoryRepository;
import com.hayasaku.console.model.dao.TagRepository;
import com.hayasaku.console.model.dto.tags.Tag;
import com.hayasaku.console.model.dto.tags.TagCategory;

/**
 * Controleurs pour la redirection vers la page de modification de profile
 * @author Lucas "Skytowz" HOTTIN
 *
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private TagCategoryRepository tagCategoryRepository;

	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping(value = {"","/"})
	public String getLogin(Model model, @RequestParam(required = false) String error) {
		model.addAttribute("tagCategories", tagCategoryRepository.findAll());
		Map<TagCategory, List<Tag>> map = new TreeMap<>((o1, o2) -> o1.getIntensity().compareTo(o2.getIntensity()));
		map.putAll(StreamSupport.stream(tagRepository.findAll().spliterator(), false).collect(Collectors.groupingBy(Tag::getTagCategory)));
		model.addAttribute("tags", map);
		return "profile";
	}
}
