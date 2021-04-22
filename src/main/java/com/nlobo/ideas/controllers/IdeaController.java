package com.nlobo.ideas.controllers;

import com.nlobo.ideas.models.Idea;
import com.nlobo.ideas.models.User;
import com.nlobo.ideas.services.IdeaService;
import com.nlobo.ideas.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class IdeaController {
    private final UserService userService;
    private final IdeaService ideaService;
    public IdeaController(UserService userService, IdeaService ideaService) {
        this.userService = userService;
        this.ideaService = ideaService;
    }

    @RequestMapping(value = "/ideas")
    public String ideas(HttpSession session, Model model) {
        if (session.getAttribute("isHighToLow") == null) {
            session.setAttribute("isHighToLow", true);
        }
        User user = userService.findUserById((Long)session.getAttribute("userId"));
        List<Idea> ideasLowToHigh = ideaService.getAllByLikesCountAsc();
        List<Idea> ideasHighToLow = ideaService.getAllByLikesCountDesc();
        model.addAttribute("user", user);
        if ((Boolean) session.getAttribute("isHighToLow")) {
            model.addAttribute("ideas", ideasHighToLow);
        } else {
            System.out.println("***** TRIGGERED THE TOGGLE *****");
            model.addAttribute("ideas", ideasLowToHigh);
        }
        return "ideas.jsp";
    }

    @RequestMapping(value = "/ideas/false")
    public String ideasFalse(HttpSession session) {
        session.setAttribute("isHighToLow", false);
        return "redirect:/ideas";
    }

    @RequestMapping(value = "/ideas/true")
    public String ideasTrue(HttpSession session) {
        session.setAttribute("isHighToLow", true);
        return "redirect:/ideas";
    }

    @RequestMapping(value = "/ideas/new", method = RequestMethod.GET)
    public String ideaCreateForm(HttpSession session, Model model) {
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        model.addAttribute("user", user);
        model.addAttribute("idea", new Idea());
        return "ideaCreateForm.jsp";
    }

    @RequestMapping(value = "/ideas/new", method = RequestMethod.POST)
    public String ideaCreate(
            HttpSession session, Model model,
            @Valid @ModelAttribute("idea") Idea idea, BindingResult result) {
        if (result.hasErrors()) {
            User user = userService.findUserById((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
            return "ideaCreateForm.jsp";
        } else {
            ideaService.create(idea);
            return "redirect:/ideas";
        }
    }

    @RequestMapping(value = "/ideas/{ideaId}")
    public String ideaDisplay(
            HttpSession session,
            @PathVariable("ideaId") Long ideaId, Model model) {
        Idea idea = ideaService.findIdeaById(ideaId);
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        model.addAttribute("idea", idea);
        model.addAttribute("user", user);
        return "ideaDisplay.jsp";
    }

    @RequestMapping(value = "/ideas/{ideaId}/edit", method = RequestMethod.GET)
    public String ideaEditForm(
            @PathVariable("ideaId") Long ideaId,
            HttpSession session, Model model) {
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        Idea idea = ideaService.findIdeaById(ideaId);
        model.addAttribute("user", user);
        model.addAttribute("idea", idea);
        return "ideaEditForm.jsp";
    }

    @RequestMapping(value = "/ideas/{ideaId}/edit", method = RequestMethod.PUT)
    public String ideaUpdate(
            HttpSession session, Model model,
            @PathVariable("ideaId") Long ideaId,
            @RequestParam("title") String title,
            @RequestParam("creator") Long creatorId,
            @Valid @ModelAttribute("idea") Idea idea, BindingResult result) {
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("idea", idea);
            return "ideaEditForm.jsp";
        } else {
            Idea ideaToUpdate = ideaService.findIdeaById(ideaId);
            ideaToUpdate.setTitle(title);
            ideaToUpdate.setCreator(user);
            Idea updatedIdea = ideaService.update(ideaToUpdate);
            return "redirect:/ideas/" + updatedIdea.getId();
        }
    }

    @RequestMapping(value = "ideas/{ideaId}/delete", method = RequestMethod.DELETE)
    public String ideaDelete(@PathVariable("ideaId") Long ideaId) {
        Idea idea = ideaService.findIdeaById(ideaId);
        ideaService.delete(idea);
        return "redirect:/ideas";
    }

    @RequestMapping(value = "ideas/{ideaId}/like")
    public String ideaLike(@PathVariable("ideaId") Long ideaId, HttpSession session) {
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        Idea idea = ideaService.findIdeaById(ideaId);
        ideaService.addLike(idea, user);
        return "redirect:/ideas";
    }

    @RequestMapping(value = "ideas/{ideaId}/unlike")
    public String ideaUnlike(@PathVariable("ideaId") Long ideaId, HttpSession session) {
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        Idea idea = ideaService.findIdeaById(ideaId);
        ideaService.removeLike(idea, user);
        return "redirect:/ideas";
    }
}
