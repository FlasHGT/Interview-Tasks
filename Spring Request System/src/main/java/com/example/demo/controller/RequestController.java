package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.demo.model.Item;
import com.example.demo.model.Attribute;
import com.example.demo.model.Request;
import com.example.demo.model.RequestItem;
import com.example.demo.service.AttributeService;
import com.example.demo.service.AttributeValService;
import com.example.demo.service.ItemService;
import com.example.demo.service.RequestItemService;
import com.example.demo.service.RequestService;
import com.example.demo.validator.AttrFormValidator;
import com.example.demo.validator.ItemFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("requests")
public class RequestController {
    
    @Autowired
    private RequestService rService;
    @Autowired
    private ItemService iService;
    @Autowired
    private AttributeService aService;
    @Autowired
    private AttributeValService aValService;
    @Autowired
    private RequestItemService rItemService;

	@GetMapping("")
	public String showRequests (Model model) {
        model.addAttribute("requests", rService.getRequests());
        // We add requests to the model, so we can create a dynamic
        // HTML structure depending on the requests

		return "requests/requests";
        // Returns a template view, not a route
    }

    @PostMapping("/remove")
	public String removeRequest (@RequestParam("requestID") Long requestID) {
        rService.deleteRequest(requestID);

		return "redirect:/requests";
    }

    @PostMapping("/status")
	public String updateStatus (@RequestParam("requestID") Long requestID, @RequestParam("status") Integer status) {
        rService.updateRequestStatusByID(requestID, status);

		return "redirect:/requests";
    }

    @GetMapping("/create")
    public String showNewRequestItem (Model model) {
        model.addAttribute("items", iService.getItems());
        // We add items to the model so we can generate a
        // select HTML structure depending on the data in the DB
        model.addAttribute(new ItemFormValidator());    
        // We use this to validate if an item is selected

        return "requests/requests_create_item";
    }

    @PostMapping("/create")
    public String handleNewRequestItem (@ModelAttribute @Valid ItemFormValidator item, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("items", iService.getItems());
            return "requests/requests_create_item";

            // If there are any errors, we need to populate the view again
            // with the necessary information, we don't need to add a new
            // ItemFormValidator, because everything related to it persists in the view
		}
        
        // Redirect to an attribute creation route (not a view)
        return "redirect:/requests/create/" + item.getItemID();
    }

    @GetMapping("/create/{item}")
    public String showNewRequest (@PathVariable("item") Long itemID, Model model) {
        Item currItem = iService.findItemByID(itemID);
        Long currGroupID = currItem.getItem_Group().getId();

        model.addAttribute("itemName", currItem.getName());
        model.addAttribute("attributes", aService.getAttributesWithItemGroupID(currGroupID));
        model.addAttribute(new AttrFormValidator());
        // Validator checks if the Reason text field is filled out.
        // In order to not check if the radio groups are filled
        // we select the last item in the radio group by default

        return "requests/requests_create_attr";
    }

    @PostMapping("/create/{item}")
    public String createNewRequest (HttpServletRequest hsr, @PathVariable("item") Long itemID, @ModelAttribute @Valid AttrFormValidator attrFormValidator, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Item currItem = iService.findItemByID(itemID);
            Long currGroupID = currItem.getItem_Group().getId();

            model.addAttribute("itemName", currItem.getName());
            model.addAttribute("attributes", aService.getAttributesWithItemGroupID(currGroupID));

            // Repopulate the view with all the necessary model information

            return "requests/requests_create_attr";
		}

        Item currItem = iService.findItemByID(itemID);

        RequestItem requestItem = new RequestItem(currItem);
        requestItem = rItemService.addRequestItem(requestItem);
        // We need this to create a new Request in the DB

        List<Attribute> attrs = aService.getAttributesWithItemGroupID(currItem.getItem_Group().getId());
        Map<String, String> attrList = new HashMap<>();
        // An associative array (key-value)

        // Never create variables in a loop, create them once and then change the value
        // don't reallocate new memory for that variable every time, just change the contents
        // of that memory field
        Attribute currAttr;
        String selectedVal;

        for (int x = 0; x < attrs.size(); x++)
        {
            currAttr = attrs.get(x); 
            // Get the current attribute in the view

            selectedVal = hsr.getParameter("" + currAttr.getId());
            // Get radio button groups selected AttributeValID

            attrList.put(currAttr.getName(), aValService.getAttrValNameByID(Long.parseLong(selectedVal)));
            // Get that AttributeVals name and the attribute name and populate the associative array
        }

        Request request = new Request(requestItem, -1, attrFormValidator.getReason(), LocalDateTime.now(), attrList); 
        rService.addNewRequest(request);
        
        return "redirect:/requests";
        // Redirect to the main page and show the updated Request table
    }
}
