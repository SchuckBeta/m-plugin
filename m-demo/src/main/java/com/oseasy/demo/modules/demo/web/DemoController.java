/**
 * .
 */

package com.oseasy.demo.modules.demo.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oseasy.demo.common.config.DemoSval;
import com.oseasy.demo.common.config.DemoSval.DemoEmskey;

/**
 * .
 * @author chenhao
 */
@RestController
public class DemoController {
    @RequestMapping(value = "demo/demo/da", method = RequestMethod.GET)
    public String demoda(Model model) {
        return DemoSval.path.vms() + "da";
    }
    @RequestMapping(value = "demoa/demoa/da", method = RequestMethod.GET)
    public String demoada(Model model) {
        return DemoSval.path.vms(DemoEmskey.DEMO.k()) + "da";
    }
    @RequestMapping(value = "demoa/demob/db", method = RequestMethod.GET)
    public String demoadb(Model model) {
        return DemoSval.path.vms(DemoEmskey.DEMO.k()) + "db";
    }
}
