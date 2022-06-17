package com.example.securityproject.controller;


import com.example.securityproject.models.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/cont")
@Slf4j
public class ManagerController {

    List<Manager> list = Stream.of(new Manager(1, "alex", "ivanov"),
            new Manager(2, "victor", "korovin"),
            new Manager(3, "gennadi", "lobov")).collect(Collectors.toList());


    /*кто имеет доступ к пермишону dev:read? -> и user и admin */
    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('dev:read')")
    public Manager getList(@PathVariable("id") int id) {
        log.info("in the controller");
        return list
                .stream()
                .filter(manager -> manager.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /*кто имеет доступ к пермишону dev:read? -> и user и admin */
    //@PreAuthorize("hasAuthority('dev:read')")
    @GetMapping("/all")
    public List<Manager> list() {
        return list;
    }

    /*кто имеет доступ к пермишону dev:write? -> only admin */
    //@PreAuthorize("hasAuthority('dev:write')")
    @PostMapping("/save")
    public Manager createManager(@RequestBody Manager manager) {
        list.add(manager);
        return manager;
    }


    /*кто имеет доступ к пермишону dev:write? -> only admin */
    //@PreAuthorize("hasAuthority('dev:write')")
    @DeleteMapping("/delete/{id}")
    public void deleteManagerById(@PathVariable("id") Integer id) {
        log.info("in the delete");
        Manager manager1 = this.list.stream().filter(manager -> manager.getId() == id).findFirst().orElse(null);
        log.info("after manager1");
        list.remove(manager1);
        log.info("after remove");
    }

}
