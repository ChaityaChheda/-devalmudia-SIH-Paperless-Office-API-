package com.example.SIH;

import Entities.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Component
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE))
public class RequestController {

    @Autowired
    FormsRepository formsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    FlowchartRepository flowchartRepository;

    @Autowired
    Pending_RequestsRepository pending_requestsRepository;

    @Autowired
    WorkflowRepository workflowRepository;

    @Autowired
    testRepository testRepository;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> userLogin(@RequestParam(required = false) String username) {
        System.out.println("Username : " + username);
        try {
            List<User> users = new ArrayList<User>();
            users = userRepository.findByUsername(username);
            //userRepository.findAll().forEach(users::add);
            for(User u : users) {
                System.out.println(u.getUsername());
            }
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable String id) {
        System.out.println("User id : " + id);
        try {
            List<User> users = new ArrayList<User>();
            users.add(userRepository.findById(id).orElse(null));
            for(User u : users) {
                System.out.println(u.getUsername());
            }
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<User>(users.get(0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);
        System.out.println("Updating details for user : " + user.getUsername());
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setAddress(user.getAddress());
            //_user.setUsername(user.getUsername());
            _user.setBranch(user.getBranch());
            _user.setContact(user.getContact());
            _user.setEmailID(user.getEmailID());
            _user.setEsign(user.getEsign());
            _user.setName(user.getName());
            //_user.setNotifications(user.getNotifications());
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/forms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> formById(@PathVariable String id) {
        System.out.println("Form id : " + id);
        try {
            List<Forms> forms = new ArrayList<Forms>();
            forms.add(formsRepository.findById(id).orElse(null));
            for(Forms u : forms) {
                System.out.println(u.getTitle());
            }
            if (forms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(forms.get(0).getJson(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/forms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> formByTitle(@RequestParam(required = false) String title) {
        System.out.println("Form Title : " + title);
        try {
            List<Forms> forms = new ArrayList<Forms>();
            if(title != null) {
                formsRepository.findByTitle(title).forEach(forms::add);
            }
            for(Forms f : forms) {
                System.out.println(f.getTitle());
            }
            if (forms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(forms.get(0).getJson(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/forms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Forms> updateForm(@PathVariable("id") String id, @RequestBody Forms forms) {
        Optional<Forms> formData = formsRepository.findById(id);
        System.out.println("Updating details for form : " + forms.getTitle());
        if (formData.isPresent()) {
            Forms _forms = formData.get();
            _forms.setTitle(forms.getTitle());
            _forms.setJson(forms.getJson());
            _forms.setSchema(forms.getSchema());
            _forms.setUischema(forms.getUischema());
            return new ResponseEntity<>(formsRepository.save(_forms), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/forms/{id}")
    public ResponseEntity<Forms> createForm(@PathVariable("id") String id, @RequestBody Forms forms) {
        try {
            Forms _forms = formsRepository.save(new Forms(id, forms.getTitle(),forms.getSchema(), forms.getUischema(), forms.getJson()));
            return new ResponseEntity<>(_forms, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @GetMapping(value = "/flowchart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> flowchartById(@PathVariable String id) {
        System.out.println("Flowchart id : " + id);
        try {
            List<Flowchart> flowcharts = new ArrayList<Flowchart>();
            flowcharts.add(flowchartRepository.findById(id).orElse(null));
            for(Flowchart u : flowcharts) {
                System.out.println(u.getTitle());
            }
            if (flowcharts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(flowcharts.get(0).getJson(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/flowchart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> flowchartByTitle(@RequestParam(required = false) String title) {
        System.out.println("Flowchart Title : " + title);
        try {
            List<Flowchart> flowcharts = new ArrayList<Flowchart>();
            if(title != null) {
                flowchartRepository.findByTitle(title).forEach(flowcharts::add);
            }
            for(Flowchart f : flowcharts) {
                System.out.println(f.getTitle());
            }
            if (flowcharts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(flowcharts.get(0).getJson(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/flowchart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flowchart> updateFlowchart(@PathVariable("id") String id, @RequestBody Flowchart flowchart) {
        Optional<Flowchart> flowchartData = flowchartRepository.findById(id);
        System.out.println("Updating details for flowchart : " + flowchart.getTitle());
        if (flowchartData.isPresent()) {
            Flowchart _flowchart = flowchartData.get();
            _flowchart.setTitle(flowchart.getTitle());
            _flowchart.setJson(flowchart.getJson());
            return new ResponseEntity<>(flowchartRepository.save(_flowchart), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/flowchart/{id}")
    public ResponseEntity<Flowchart> createFlowchart(@PathVariable("id") String id, @RequestBody Flowchart flowchart) {
        try {
            Flowchart _flowchart = flowchartRepository.save(new Flowchart(id, flowchart.getTitle(), flowchart.getJson()));
            return new ResponseEntity<>(_flowchart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu2>> getMenu() {
        try {
            List<Menu2> menu = new ArrayList<Menu2>();
            menu.addAll(menuRepository.findAll());
            for(Menu2 u : menu) {
                System.out.println(u.toString());
            }
            System.out.println(menu.size());
            if (menu.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu2> updateMenu(@RequestBody Menu2 menu) {
        List<Menu2> menuData = menuRepository.findAll();
        System.out.println("Updating details for menu");
        if (!menuData.isEmpty()) {
            Menu2 _menu = menuData.get(0);
            _menu.setContents(menu.getContents());
            return new ResponseEntity<>(menuRepository.save(_menu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/pending_requests/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pending_Requests>> getPending_Request(@PathVariable String id) {
        System.out.println("User id : " + id);
        try {
            List<Pending_Requests> pending_requests = new ArrayList<Pending_Requests>();
            pending_requests.add(pending_requestsRepository.findById(id).orElse(null));
            for(Pending_Requests p : pending_requests) {
                System.out.println(p.getId());
            }
            if (pending_requests.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pending_requests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/workflow/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getWorkflowById(@PathVariable String id) {
        System.out.println("Workflow id : " + id);
        try {
            Workflow workflow = workflowRepository.findById(id).orElse(null);
            if (workflow != null) {
                System.out.println("Worklow : " + workflow.getId());
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(workflow.getJSONString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/workflow/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Workflow> updateWorkflow(@PathVariable("id") String id, @RequestBody Workflow workflow) {
        Optional<Workflow> workflowData = workflowRepository.findById(id);
        System.out.println("Updating details for workflow : " + workflow.getTitle());
        if (workflowData.isPresent()) {
            Workflow _workflow = workflowData.get();
            _workflow.setComments(workflow.getComments());
            _workflow.setComponentId(workflow.getComponentId());
            _workflow.setEnd_timestamp(workflow.getEnd_timestamp());
            _workflow.setFlowChart(workflow.getFlowChart());
            _workflow.setFormData(workflow.getFormData());
            _workflow.setNextNodes(workflow.getNextNodes());
            _workflow.setPath(workflow.getPath());
            _workflow.setRejected(workflow.isRejected());
            _workflow.setSignatures(workflow.getSignatures());
            _workflow.setStart_timestamp(workflow.getStart_timestamp());
            _workflow.setStatus(workflow.getStatus());
            _workflow.setTitle(workflow.getTitle());
            _workflow.setUser(workflow.getUser());
            return new ResponseEntity<>(workflowRepository.save(_workflow), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/workflow")
    public ResponseEntity<Workflow> createWorkflow(@RequestBody Workflow workflow) {
        try {
            Workflow _workflow = workflowRepository.save(workflow);
            return new ResponseEntity<>(_workflow, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/addMenu")
    public ResponseEntity<Forms> addMenu() {

        HashMap<String, List<idTitle>> m1 = new HashMap<String, List<idTitle>>() {
            {
                put("New", new ArrayList<idTitle>(Arrays.asList(
                        new idTitle(1, "Start a Custom WorkFlow"),
                        new idTitle(2, "two")
                )));

                put("Academics", new ArrayList<>(Arrays.asList(
                        new idTitle(1, "Admission Cancellation"),
                        new idTitle(2, "No Dues Form"),
                        new idTitle(3, "Library Registration")
                )));

                put("Store",new ArrayList<>(Arrays.asList(
                        new idTitle(4, "Tender 1"),
                        new idTitle(5, "Random Form")
                )));

                put("Custom", new ArrayList<>(Arrays.asList(
                        new idTitle(651,"Application for GSEC"),
                        new idTitle(993,"Application for TechSec"),
                        new idTitle(197,"Application for LR"),
                        new idTitle(620,"Application for ACM President"),
                        new idTitle(164,"Application for IEEE President"),
                        new idTitle(543,"Application for Sports Secretary")
                )));
            }
        };


        Menu2 menu = new Menu2(0, m1);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/addUser")
    public ResponseEntity<Forms> addUser() {

        HashMap<String, titleContent> m1 = new HashMap<String, titleContent>() {
            {
                put("n1", new titleContent("Pending Approval for Application of Sports Secretary", "Chaitya Chheda BT16CSE073 Stage 1 request for approval for application for Sports Secretary is yet to be approved by you. Due data 07/07/2020"));

                put("n2", new titleContent("Pending Approval for Application of Sports Secretary", "Chaitya Chheda BT16CSE073 Stage 1 request for approval for application for Sports Secretary is yet to be approved by you. Due data 07/07/2020"));

                put("n3", new titleContent("Pending Approval for Application of Sports Secretary", "Chaitya Chheda BT16CSE073 Stage 1 request for approval for application for Sports Secretary is yet to be approved by you. Due data 07/07/2020"));
            }
        };


        User user2 = new User("HOD001", "Umesh Deshpande",
                "UAD",
                "ritwikdeshpande01@gmail.com",
                "Andheri",
                "Computer Science Department",
                9820188402L,
                "umesh",
                "data:image/png;base64,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",
                m1);

        User user3 = new User("AP001", "Ravindra Keskar",
                "RBK",
                "ritwikdeshpande01@gmail.com",
                "Andheri",
                "Computer Science Department",
                9820188402L,
                "keskar",
                "data:image/png;base64,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",
                m1);

        User user4 = new User("AP002", "Anil Mokhade",
                "MH",
                "ritwikdeshpande01@gmail.com",
                "Andheri",
                "Computer Science Department",
                9820188402L,
                "Mokhade",
                "data:image/png;base64,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",
                m1);

        User user5 = new User("DIR01", "Pramod Padole",
                "PD",
                "ritwikdeshpande01@gmail.com",
                "Andheri",
                "Computer Science Department",
                9820188402L,
                "Padole",
                "data:image/png;base64,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",
                m1);

        User user6 = new User("5", "MHRD",
                "MHRD",
                "ritwikdeshpande01@gmail.com",
                "Andheri",
                "Computer Science Department",
                9820188402L,
                "MHRD",
                "data:image/png;base64,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",
                m1);

        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/addForms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addForm() {
    String schema = null;
    try {
            String s = "{ \"title\": \"Application for Sports Secretary\", \"description\": \"A Custom Form Created\", \"type\": \"object\", \"required\": [], \"properties\": { \"Upload_Documents\": { \"type\": \"array\", \"items\": { \"type\": \"string\", \"format\": \"data-url\" } }, \"fullname\": { \"type\": \"string\", \"title\": \"Full Name\" } } }";
            JSONObject jsonObject = new JSONObject("{ \"title\": \"Application for Sports Secretary\", \"description\": \"A Custom Form Created\", \"type\": \"object\", \"required\": [], \"properties\": { \"Upload_Documents\": { \"type\": \"array\", \"items\": { \"type\": \"string\", \"format\": \"data-url\" } }, \"fullname\": { \"type\": \"string\", \"title\": \"Full Name\" } } }");
            System.out.println(jsonObject.toString());
            schema = jsonObject.toString();
    }catch (JSONException err) {
        }
        Forms form = new Forms("543", "Application for Sports Secretary", schema, "", "{\n" +
                "      \"id\": \"543\",\n" +
                "      \"title\": \"Application for Sports Secretary\",\n" +
                "      \"schema\": {\n" +
                "        \"title\": \"Application for Sports Secretary\",\n" +
                "        \"description\": \"A Custom Form Created\",\n" +
                "        \"type\": \"object\",\n" +
                "        \"required\": [],\n" +
                "        \"properties\": {\n" +
                "          \"Upload_Documents\": {\n" +
                "            \"type\": \"array\",\n" +
                "            \"items\": {\n" +
                "              \"type\": \"string\",\n" +
                "              \"format\": \"data-url\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"fullname\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"title\": \"Full Name\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }");
        formsRepository.save(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/addFlowchart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addFlowchart() {
        Flowchart flowchart = new Flowchart("543","Application for Sports Secretary", "{\n" +
                "      \"id\": 543,\n" +
                "      \"title\": \"Application for Sports Secretary\",\n" +
                "      \"chart\": {\n" +
                "        \"offset\": {\n" +
                "          \"x\": -88,\n" +
                "          \"y\": -160\n" +
                "        },\n" +
                "        \"nodes\": {\n" +
                "          \"node1\": {\n" +
                "            \"id\": \"node1\",\n" +
                "            \"type\": \"Start\",\n" +
                "            \"properties\": {\n" +
                "              \"approvers\": \"RB Keskar-Manish Kurehkar\"\n" +
                "            },\n" +
                "            \"position\": {\n" +
                "              \"x\": 208,\n" +
                "              \"y\": 63\n" +
                "            },\n" +
                "            \"ports\": {\n" +
                "              \"port2\": {\n" +
                "                \"id\": \"port2\",\n" +
                "                \"type\": \"output\",\n" +
                "                \"position\": {\n" +
                "                  \"x\": 110,\n" +
                "                  \"y\": 278\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"size\": {\n" +
                "              \"width\": 220,\n" +
                "              \"height\": 277\n" +
                "            }\n" +
                "          },\n" +
                "          \"node2\": {\n" +
                "            \"id\": \"node2\",\n" +
                "            \"type\": \"End\",\n" +
                "            \"position\": {\n" +
                "              \"x\": 197,\n" +
                "              \"y\": 881\n" +
                "            },\n" +
                "            \"properties\": {\n" +
                "              \"approvers\": \"\"\n" +
                "            },\n" +
                "            \"ports\": {\n" +
                "              \"port1\": {\n" +
                "                \"id\": \"port1\",\n" +
                "                \"type\": \"input\",\n" +
                "                \"position\": {\n" +
                "                  \"x\": 110,\n" +
                "                  \"y\": 0\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"size\": {\n" +
                "              \"width\": 220,\n" +
                "              \"height\": 172\n" +
                "            }\n" +
                "          },\n" +
                "          \"7f4cb6bf-ff74-4189-a602-1e9ee4f40946\": {\n" +
                "            \"id\": \"7f4cb6bf-ff74-4189-a602-1e9ee4f40946\",\n" +
                "            \"position\": {\n" +
                "              \"x\": 523.0138854980469,\n" +
                "              \"y\": 318.56945037841797\n" +
                "            },\n" +
                "            \"orientation\": 0,\n" +
                "            \"type\": \"Intermediate Nodes\",\n" +
                "            \"ports\": {\n" +
                "              \"port1\": {\n" +
                "                \"id\": \"port1\",\n" +
                "                \"type\": \"input\",\n" +
                "                \"position\": {\n" +
                "                  \"x\": 110,\n" +
                "                  \"y\": 0\n" +
                "                }\n" +
                "              },\n" +
                "              \"port2\": {\n" +
                "                \"id\": \"port2\",\n" +
                "                \"type\": \"output\",\n" +
                "                \"position\": {\n" +
                "                  \"x\": 110,\n" +
                "                  \"y\": 224\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"properties\": {\n" +
                "              \"approvers\": \"UAD\"\n" +
                "            },\n" +
                "            \"size\": {\n" +
                "              \"width\": 220,\n" +
                "              \"height\": 224\n" +
                "            }\n" +
                "          },\n" +
                "          \"225b1c88-9dfa-4d2e-8774-f48c5ad00770\": {\n" +
                "            \"id\": \"225b1c88-9dfa-4d2e-8774-f48c5ad00770\",\n" +
                "            \"position\": {\n" +
                "              \"x\": 541.0138854980469,\n" +
                "              \"y\": 594.569450378418\n" +
                "            },\n" +
                "            \"orientation\": 0,\n" +
                "            \"type\": \"Intermediate Nodes\",\n" +
                "            \"ports\": {\n" +
                "              \"port1\": {\n" +
                "                \"id\": \"port1\",\n" +
                "                \"type\": \"input\",\n" +
                "                \"position\": {\n" +
                "                  \"x\": 110,\n" +
                "                  \"y\": 0\n" +
                "                }\n" +
                "              },\n" +
                "              \"port2\": {\n" +
                "                \"id\": \"port2\",\n" +
                "                \"type\": \"output\",\n" +
                "                \"position\": {\n" +
                "                  \"x\": 110,\n" +
                "                  \"y\": 224\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"properties\": {\n" +
                "              \"approvers\": \"Mokhade\"\n" +
                "            },\n" +
                "            \"size\": {\n" +
                "              \"width\": 220,\n" +
                "              \"height\": 224\n" +
                "            }\n" +
                "          },\n" +
                "          \"a6ed13ba-3364-49b8-8a8f-49f8cce923e5\": {\n" +
                "            \"id\": \"a6ed13ba-3364-49b8-8a8f-49f8cce923e5\",\n" +
                "            \"position\": {\n" +
                "              \"x\": 166.01388549804688,\n" +
                "              \"y\": 497.7916679382324\n" +
                "            },\n" +
                "            \"orientation\": 0,\n" +
                "            \"type\": \"Intermediate Nodes\",\n" +
                "            \"ports\": {\n" +
                "              \"port1\": {\n" +
                "                \"id\": \"port1\",\n" +
                "                \"type\": \"input\",\n" +
                "                \"position\": {\n" +
                "                  \"x\": 110,\n" +
                "                  \"y\": 0\n" +
                "                }\n" +
                "              },\n" +
                "              \"port2\": {\n" +
                "                \"id\": \"port2\",\n" +
                "                \"type\": \"output\",\n" +
                "                \"position\": {\n" +
                "                  \"x\": 110,\n" +
                "                  \"y\": 224\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"properties\": {\n" +
                "              \"approvers\": \"RB Keskar\"\n" +
                "            },\n" +
                "            \"size\": {\n" +
                "              \"width\": 220,\n" +
                "              \"height\": 224\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"links\": {\n" +
                "          \"5b6ee711-fee7-41ce-b0d7-cbad2003eb68\": {\n" +
                "            \"id\": \"5b6ee711-fee7-41ce-b0d7-cbad2003eb68\",\n" +
                "            \"from\": {\n" +
                "              \"nodeId\": \"node1\",\n" +
                "              \"portId\": \"port2\"\n" +
                "            },\n" +
                "            \"to\": {\n" +
                "              \"nodeId\": \"7f4cb6bf-ff74-4189-a602-1e9ee4f40946\",\n" +
                "              \"portId\": \"port1\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"622c2199-16b5-4ce0-bc6d-01f80d15f48a\": {\n" +
                "            \"id\": \"622c2199-16b5-4ce0-bc6d-01f80d15f48a\",\n" +
                "            \"from\": {\n" +
                "              \"nodeId\": \"7f4cb6bf-ff74-4189-a602-1e9ee4f40946\",\n" +
                "              \"portId\": \"port2\"\n" +
                "            },\n" +
                "            \"to\": {\n" +
                "              \"nodeId\": \"225b1c88-9dfa-4d2e-8774-f48c5ad00770\",\n" +
                "              \"portId\": \"port1\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"86b4be92-0510-4683-aa39-5013742d7de2\": {\n" +
                "            \"id\": \"86b4be92-0510-4683-aa39-5013742d7de2\",\n" +
                "            \"from\": {\n" +
                "              \"nodeId\": \"225b1c88-9dfa-4d2e-8774-f48c5ad00770\",\n" +
                "              \"portId\": \"port2\"\n" +
                "            },\n" +
                "            \"to\": {\n" +
                "              \"nodeId\": \"node2\",\n" +
                "              \"portId\": \"port1\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"c875f423-fe03-4a7b-87c7-932afd069ffe\": {\n" +
                "            \"id\": \"c875f423-fe03-4a7b-87c7-932afd069ffe\",\n" +
                "            \"from\": {\n" +
                "              \"nodeId\": \"node1\",\n" +
                "              \"portId\": \"port2\"\n" +
                "            },\n" +
                "            \"to\": {\n" +
                "              \"nodeId\": \"a6ed13ba-3364-49b8-8a8f-49f8cce923e5\",\n" +
                "              \"portId\": \"port1\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"75082463-70a9-4590-b441-5aba9026e427\": {\n" +
                "            \"id\": \"75082463-70a9-4590-b441-5aba9026e427\",\n" +
                "            \"from\": {\n" +
                "              \"nodeId\": \"a6ed13ba-3364-49b8-8a8f-49f8cce923e5\",\n" +
                "              \"portId\": \"port2\"\n" +
                "            },\n" +
                "            \"to\": {\n" +
                "              \"nodeId\": \"node2\",\n" +
                "              \"portId\": \"port1\"\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"selected\": {\n" +
                "          \"type\": \"node\",\n" +
                "          \"id\": \"a6ed13ba-3364-49b8-8a8f-49f8cce923e5\"\n" +
                "        },\n" +
                "        \"hovered\": {}\n" +
                "      }\n" +
                "    }");
        flowchartRepository.save(flowchart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/addWorkflow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Workflow> addWorkFlow() {
        /*String schema = null;
        try {
            String s = "{ \"title\": \"Application for Sports Secretary\", \"description\": \"A Custom Form Created\", \"type\": \"object\", \"required\": [], \"properties\": { \"Upload_Documents\": { \"type\": \"array\", \"items\": { \"type\": \"string\", \"format\": \"data-url\" } }, \"fullname\": { \"type\": \"string\", \"title\": \"Full Name\" } } }";
            JSONObject jsonObject = new JSONObject("{ \"title\": \"Application for Sports Secretary\", \"description\": \"A Custom Form Created\", \"type\": \"object\", \"required\": [], \"properties\": { \"Upload_Documents\": { \"type\": \"array\", \"items\": { \"type\": \"string\", \"format\": \"data-url\" } }, \"fullname\": { \"type\": \"string\", \"title\": \"Full Name\" } } }");
            System.out.println(jsonObject.toString());
            schema = jsonObject.toString();
        }catch (JSONException err) {
        }*/

        List<Comment> comments = new ArrayList<>(Arrays.asList(new Comment(0,"MHRD", "Sorry rejecting the application", "Jun  6  at  0:13,   2020")));

                Workflow w = new Workflow("5v0", "Application for Sports Secretary","rd", "p", "p", "543", "active",
                "{\n" +
                        "        \"Upload_Documents\": [],\n" +
                        "        \"Fullname\": \"Chaitya Chheda\",\n" +
                        "        \"Branch\": \"Computer Science and Engineering\",\n" +
                        "        \"Statement\": \"This is Chaitya Chheda, I want to apply for Student Council for the post of Sports Secretary\"\n" +
                        "      }",
                "{\n" +
                        "        \"node1\": {\n" +
                        "          \"approvedBy\": {\n" +
                        "            \"AP001\": true\n" +
                        "          },\n" +
                        "          \"timestamp\": {\n" +
                        "            \"AP001\": \"Jun  5  at  16:42,   2020\"\n" +
                        "          },\n" +
                        "          \"nextNodes\": [\n" +
                        "            \"7f4cb6bf-ff74-4189-a602-1e9ee4f40946\",\n" +
                        "            \"a6ed13ba-3364-49b8-8a8f-49f8cce923e5\"\n" +
                        "          ],\n" +
                        "          \"type\": \"Start\"\n" +
                        "        },\n" +
                        "        \"7f4cb6bf-ff74-4189-a602-1e9ee4f40946\": {\n" +
                        "          \"approvedBy\": {\n" +
                        "            \"HOD001\": true\n" +
                        "          },\n" +
                        "          \"timestamp\": {\n" +
                        "            \"HOD001\": \"Jun  5  at  20:42,   2020\"\n" +
                        "          },\n" +
                        "          \"nextNodes\": [\n" +
                        "            \"225b1c88-9dfa-4d2e-8774-f48c5ad00770\"\n" +
                        "          ],\n" +
                        "          \"type\": \"Intermediate Node\"\n" +
                        "        },\n" +
                        "        \"225b1c88-9dfa-4d2e-8774-f48c5ad00770\": {\n" +
                        "          \"approvedBy\": {\n" +
                        "            \"AP002\": true,\n" +
                        "            \"DIR01\": true\n" +
                        "          },\n" +
                        "          \"timestamp\": {\n" +
                        "            \"AP002\": \"Jun  5  at  21:42,   2020\",\n" +
                        "            \"DIR01\": \"Jun  5  at  21:42,   2020\"\n" +
                        "          },\n" +
                        "          \"nextNodes\": [\n" +
                        "            \"node2\"\n" +
                        "          ],\n" +
                        "          \"type\": \"Intermediate Node\"\n" +
                        "        },\n" +
                        "        \"node2\": {\n" +
                        "          \"approvedBy\": {\n" +
                        "            \"MHRD\": false\n" +
                        "          },\n" +
                        "          \"timestamp\": {\n" +
                        "            \"MHRD\": null\n" +
                        "          },\n" +
                        "          \"nextNodes\": [],\n" +
                        "          \"type\": \"End\"\n" +
                        "        },\n" +
                        "        \"a6ed13ba-3364-49b8-8a8f-49f8cce923e5\": {\n" +
                        "          \"approvedBy\": {\n" +
                        "            \"\": false\n" +
                        "          },\n" +
                        "          \"nextNodes\": [\n" +
                        "            \"node2\"\n" +
                        "          ],\n" +
                        "          \"timestamp\": \"\",\n" +
                        "          \"type\": \"Intermediate Nodes\"\n" +
                        "        }\n" +
                        "      }",
                        comments,
                new ArrayList<>(Arrays.asList("node1",
                        "7f4cb6bf-ff74-4189-a602-1e9ee4f40946",
                        "225b1c88-9dfa-4d2e-8774-f48c5ad00770",
                        "node2")),
                new ArrayList<>(Arrays.asList("p")),
                new HashMap<String, String> (){
            {
                put( "Ravindra Keskar", "data:image/png;base64,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");
                    put("Umesh Deshpande", "data:image/png;base64,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");
                    put("Anil Mokhade", "data:image/png;base64,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");
                    put("Pramod Padole", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAPAAAADwCAYAAAA+VemSAAAWKklEQVR4Xu2dCcxGR1WGXwihFiiyCWURhUBJQVlFWlaXQssmVRERIcgiBhCE0gAaDZsLKmvLHhYJFAhCirayCrLJjihbqMhmoFB2sUBFDeSFGTIZ7v2/+313+ebc/5mkKeW/y5lnzvvNneWcuZAoEIBAWAIXCms5hkMAAkLAOAEEAhNAwIEbD9MhgIDxAQgEJoCAAzcepkMAAeMDEAhMAAEHbjxMhwACxgcgEJgAAg7ceJgOAQSMD0AgMAEEHLjxMB0CCBgfgEBgAgg4cONhOgQQMD4AgcAEEHDgxsN0CCBgfAACgQkg4MCNh+kQQMD4AAQCE0DAgRsP0yGAgPEBCAQmgIADNx6mQwAB4wMQCEwAAQduPEyHAALGByAQmAACDtx4mA4BBIwPQCAwAQQcuPEwHQIIGB+AQGACCDhw42E6BBAwPgCBwAQQcODGw3QIIGB8AAKBCSDgwI2H6RBAwPgABAITQMCBGw/TIYCA8QEIBCaAgAM3HqZDAAHjAxAITAABB248TIcAAsYHIBCYAAIO3HiYDgEEjA9AIDABBBy48TAdAggYH4BAYAIIOHDjYToEEDA+AIHABBBw4MbDdAggYHwAAoEJIODAjYfpEEDA+AAEAhNAwIEbD9MhgIDxAQgEJoCAAzcepkMAAeMDEAhMAAEHbjxMhwACxgcgEJgAAg7ceJgOAQSMD0AgMAEEHLjxMB0CCBgfgEBgAlEE/BOSjpL0ycCsMR0CkxNoUcCXknS8pHtJur2kixW1/rSkb0o6TdKXJZ0j6SOTU+GBEAhCYN8Cdq/6G5KumsR6YUlXknT0FvzOk/RtSa+X9P70bwudAoHVE5hLwNeW9AuSPpoIflHSVyUdJ+n6km4l6YjU084B+d8lnVs9+DmSXjrHy3gmBPZFYGoBP0HSzSXdZIIK+dP4C5IuSJ/Lx0wg+PdKerWkR09gH4+AwN4JTCXgy0j6e0k3G1mj10l6k6Szi967fOTlJN1B0knpR8Kfyu7pty2PQcTbIuP6FglMIeCflvQKSTfaoYJfkvS36Z8373C/b7mKpBtK+oakn5N0HUlHSvrNA573WUn3kLTrO3c0ldsgMC2BKQT8dEkPGGDWd9Ks8dskWbivlPShAfdNcckfSDolTZbl5/kT/dckebxMgUBIAmMF7N73U1XN/1/SX0j6k+Lz1pNYeUJrX6A82/2o1ENnG06X9OB9GcR7ITCWwFgB30/Ssysj7pI+icfaNsf9FvHLqwePZTCHnTwTAoMIjHVei9cizuWdkm466M37ucjrzp5sKye+Wv7B2Q8l3hqGwFgB+/PZn9G5/E3aQdUygHtLel5h4JPT+Lhlm7ENAp0Exgr4u9VTIyzP1ON2T2b9DP4BgYgExgjYO6o+UFX6F4MszbxB0gmF7ZeW9PWIDYjNh5vAGAGfLOnMCt+Pp/XY1qnabtufS5Qfnta5Yt/CBMYI2BNB/1TZO+Z5S1bdWym9pJSLN3RYxBQIhCIwRnBdSzLeCeWIoAilHL9/Lu3oimA3NkLghwTGCNgPeV+1hfI9EwUyLNFE/yjpl/mMXgI175iLwFgBv0DS71TGPVTSU+YyeMLnPl7SI4rnnZhiiSd8BY+CwLwExgq4ayulLY6wnFTvIouwhj2vN/D0cATGCtgVfk0K76sr/+4UwN8qlHoSjomsVlsKu3oJTCFgP9zhhL/e8ZYzJN29Uf61gP8tZQtp1FzMgsCPEphKwH5yvTST39byVsXzJV08GeoUPFfGSSAQicAuArYgXTxZVReL+EGSnKEjF2fN8ARRi3G3zpF112Sos1w6fS0FAmEIbCvg35L0klQ7Z9KwmB2BVJa/lnRq9f85mD4LvyU49Uw0kUkttQ62bCSwrYCvK8ljxbJYmI+t9hI7b7N74rJcNmWm3GjUghfU4+AIs+cL4uFVrRPYVsCuj1PGdiVTL53fiefOqirf6jJNuSPrg5Ku13qjYR8EMoFdBJzv7Zq0srD/LOVfds/8kAr1fSQ9vzH8jkJyEIaLU/9coTH7MAcCvQTGCNgP9UYO78YqM1xYEL8qyZNXdb4sj5s9zmyp+AfFx7i4/KukG7RkHLZA4CACYwWcn10HNrgn/uN0bMrdCgN8rpE/UT/RULOU20H9o3O1hmzDFAgcSGAqAV8ybea4dfG2nOnCpyuUn6WtjYURMCIJS2AqAefP6b9KvW4G4omtS0h6WEXIkUCl2PcJsBSwQyEdEkmBQAgCUwrYFa4/pZ3A3Wcl/WklbF/rz9UXNnDESTkZh4BDuC1GZgJTC9jPraN8Hph2YXkDSN9Op32uvzqrSDkJNwcTPA4CsxCYw1mvKMmB/T6zyCVH+VxDkiOUym2WZaX2NTZGwLO4Fg9dgsAcAu7qhX9X0nNTT+dcVH0nCj61Y+14bg7eCupzi13eNcERpnPby/Mh8EMCcwnYL3BwgLdPurwqrQ3nF7uXflw6XMwpXctiMbmnXqq8VdIt0st88Notl3ox74HAWAJzCvhl1RGfXl/1xFVZPIHk3Vp5J5T/9hxJvze2YlvcX05i7XMsvoXJXAqBHxCYU8CXSoLN4vy7KhdzboPjJb2jahCfr1RHOc3VZuUy0jMkedKNAoEQBOYUsAE4uZ3P5s3F2xS9XbEu9b7qJXvCUsDu+f0FQIFACAJzC9h7pS3Y3At73bfOYmlQdS/8FUmXW4hgKeAlfzgWqh6vWTOBuQVsdl4eumcBsWss7D+Xk0n+76WSxJdHpC49/l6zb1G3BQgsIeA69WxfL1xnx1iqNywFzBh4AafjFdMRWELAdS/scEOPhesZaS8tOdtH3q211LGfZUD/Uj8a07UgTzrUBJYScN0L9wnl6ZIekFpkiX3JpNQ51O4fv/JLCbhrLNx1Ju+N0nlLmezcx37Wa9Vzvy++x1CDpggsKeD6QPCubZNL94hvlPRLqUVIK9uUa2LMEAJLCtj2eEvlnZJhHgt7Rtr/LovzUuVxsFPdeBZ7juKNJl8rHtw3uTbHu3kmBCYhsLSAh/Sw5aTSnOcVeT3aa8C5OI+Xf2AoEAhDYGkBG4xFeatEqCsHldPROi2ty9uLQIOpoZZfA/8lyT0yBQKhCOxDwHUvXH8ml/G5H5d0zAxE+XyeASqPXJ7APgTsWnp7ZU6gXvfCSySZcwRUedQLn8/L+x5vnIDAvgRcjz/L5ZtyZ9RcaV793J9K/Ph8nsCReMR+COxLwK5tKaIy1PBzkq6UcMyxmeNkSWcWuPeRBWQ/rc1bV0dgnwKuQwi9vdJjU4+ByzK1jeUkmt/TF1yxusamQusjMLU4tiVUnkvkdViv+c4p4HoCrS/JwLb1aOF61+1j6ZDyn00G+SSM90r6P0mfLYx0+l+n/D1S0mtaMB4bdiOwbwHXvbDPH/ah23P1wHVoY1+Cgd1oTnuXT4E8IeUS8+Ho3mb635KOSrnGcjCIN71cPJ1TdZAFFrPvuU7HRd6b7n3oFjUlEIF9C7hezrEj/X6RDO/85LBTIK0DKt5yQHbMKd63yzMckeWsIF4n78vcuctzh9zTd2D7kHu5Zk8E9i1gV7tMu+Me4lvpDGL/zf/76NTzjEVUp/dpaezrH7JnpuNmcibPsfXd5f5zU6bQJbOC7mIn9yQCLQi47hkdB1x+5k2VmaMeb3el9tmXY9SnWdR2+IftoumEi65P6Mun/eNOReTD5Dy2NcfPp/v8I/i/6b9zskD/UHjI4k/zsswx878vrqt/bwsCNuRybGoHtMPlMkWQfbl05HVfR0bVCQX22dj16RC2xecWvzaNSz1zPldxon3PRZTl/pKeNdcLee50BFoRcD07XNbQEzjXGlnl8geixagjn5d89aKOfynpkSPrvM3t9Q/IN6pc3ds8i2sXJNCKgF3lcn3WIYX+LMxlbKB9+fnclUhgQeSdryozkfgCzzb7zOWlij+lffhcWY5Ny1JL2cB7diDQkoDrHVJldcZ8RpfPbbH3dT1vks5lKus89kdrG3eoVwN87xjm27yba0cQaEnAroYnXcrxb67aV4ulpW2rW84+LymKbey8ctrAcs3ipjljobtse0Nad85/Q8DbtOCerm1NwE5o58/J/BnpGVeX/5H0kztuNPhwmtV2xktPXrVWzpB0tx6jllzqqjfVLJUVtLX2CGVPawI2vHN6YoDvIsmbDbYp5RKVx3lOYtdSqUVT2/ZESacuZHAZxulXek3YXwaUhgm0KOCHSnpSYuY9vBdJ/9vitYi3KTnu10tHFnOdf2ubZ81xbdfyUfke71/2l8cSpbZl6U/4Jeq4une0KOCuCZ0Mflt788x2i0ELBy2dlY621Fi0FvBS712dqJas0LaCWMq2vp7JTu89zENKObPqXt2TWS2VWsBee+1bOlqincpkguaEgFvylh5blnCMXTD0jQ29IcM5tIaUMuvHkpNBQ2zzNeWEnf/bCfxu3nOz0/+cMvTBO17nYUb5A9Kqb+xYvXXe1moj9X1eOrb15wc2Rd591ersc9ePlIMIPISoi2N2HcPrkMA5ije3/EsRkvhqSbef40U8c1oCrQrYtfQETj0Luo0Yc8qeVlPm3FTSP1fN6eNNHdjQVXx9DkSY1guk+0h6bvHQXSYMp7aJ5w0g0LKA/dnoWeS6DFlOKpePWs042bX7yXW9QNKPddR7zrOLHYF0w+KdrTIb4NKH65KWBexPxpd3NMeQ3qH8BG9x73Ouluvneg4tc+wku7ukFxUGeKntKjN+rg+tK9cNINCygOuTCsvqbLI7jy9bTxnrfdrehTVUxNtM4g1o/u9fUsdjLx0JNdROrusgsEkI+4ZWL21kezb1RHn9t8W0OTXTy6R43OMk3XgAcPeOTr07ZfGM/R0l/ackr5nPGX88pd2H/lmtC9hBDP4ErsumNco8gbXputYc4E2S/ON0UIlWp9YYr8qe1gVcHkCWMzK6AbyscrsDWiL33BEnY46X5B6xbzbaR6I68d22+8JX5bhU5gcEWhdwfQRLbrd3SLpZTyOWE1gtbuAY4ntOFetZ+N8+4GIL2AEI5HUeQnSl17Qu4L4gf4/V8tlGddOUom+9fpvcalO0ku/32rDFTo+8ieYK/966gzu5ueNSu0qf7XkHVoQJrKEu9TRJv7IhMimfwuB6e+jhEyApKyfQuoCNPwfk103Rl242H126xsmeIT1y5kQ44MrFG2EMbBv7IpO6lpLK3U0RJ7CGuJxPb7hnWj/OZyD13edsH96oQVkpgQg9cF+v09XDOmXOByS1GsA/tRuZjWerLeq+cktJb5v6xTyvDQIRBNw3Du4ScM7AsU3QQxstMc4KT9z5PCXvqqrPVHL4pecFKCskEEHAfePgrj3Rr0/nC61x/DvU/Twr7V1duSDgoeQCXhdFwHXwu1HXAr5COhfIf/NJDj7R4TCWes5grXMBh7Ftf6TOUQTsGWcH85elTtJ+YjrPx7mlHTt7GMsTJD2sqLi3lF53otMdDyPP5uscRcAGWQc22Dm90yqXPNk1R8RO8w2ZIppenE4jzPYSWRSh5UbYGEnA9adhfQzm6yTd5hAmY/OklfNlOZqoLB4L/xGRRSPUEeDWSALuWk7yOcIfTTOvFrhLpDqNcRH3rs4PVs86+5kOyvfYl7DAMYQD3BvJ2buWk/JmjtMkPUiStxz632suHuM+Ih3o3VVPi9az8Ih3zV4QtLf6VJE50VXIAv64pGuklLNrXPP0sTDuaftCDJ0A8D8kPU+Sx8GUQ0IgUg/sJvFn48OLtnFP8z5JZwX9QepzM39t3EDSH6aD2fqu86Fvz5Z0Jj3uIVFsVc1oAq7zRTsW1lE4dx4Q5N96Czsf9G1TXTy231T8peEfMM/GUw4pgWgCvkQ6YjSnXf2kpKuntnOGjmjB7c6H5c/eq1ZpXfvc0dk4PN73hB4FAiFnbH2CgD8v6xLpx8hRU963fY/iB6jPHT2j7N72jZLOxmchUBKI5PTZbp8g4JMEyvIMSQ8M0rROl/t4SSf02OtPYu/p9jq3t4MymxykYfdhZkQBd+XJOlrSefsAuMU7HSnk2eQ/77jH2z+dztUJ1p3viwKBQQQiCrhO+O7Us5cdVNv9XeRZZfeo9ZEpDsjw+UjPkuQZZQoEtiIQUcBHSvpWVcuW69F10uLpkv5Bkrd/UiCwM4GWHb+vUl1nJm06qWFnQDvcaMF+MW0ycRSVP/lz8YYT7xTzPmUf6E2BwCgCEQXcdSDYNucG7wLMoszCdO/vnFQunnD6jqRjevYkl+86zEkGdmHOPQMIRBNwvROrrOKUvbDHrKem3NMXHiDOg1CTgH2AI3LJbgQiCfgOxZZJ54r+UiWsOj54FyJem73vhu2LQ57rdVvnZ17jvuwh9eeahQhEEnAZTuje9qQUlVOiem3ajngQPs9iu3jr4uXTiYDnpzGrjzTZVPxDkbcv+rwmLwH5Hxev2bJuu4kgf5+MQBQBlzO55Viyazxc18kpV++Udm9ZvFnAmyCeK+ntkl6RemTEuYkYf1+cQBQBn5Mmitzj+UDsvKXQn9UeF3vMmot3OTmK566SHivpmltS9Zg1/7PlrVwOgWUJRBBwufPKPeItKkQObrdoc7lAkvNC+xN5aPF6rM/mfaWkTwy9iesgsG8CEQRcJrPzYd/e3F8Wj1u97jq0sNd4KCmua55A6wIux7gOVnDQQl36zhAur/uYJO9+cpCAM1dQILAKAi0L2OccvVXSUZI+mCai3Hu6x/WklrMw+tyfvnOC3UAey/rsXO98okBgdQRaFrDXevPklFPmWMj+Z+gssnvcB6+uxagQBAoCLQvYqXIutmNrOWzvZTvey20QCEOgZQHXh3R1QXU2Rie1O7n6o09sIFdUGDfE0F0JtCxgj3N9yp7PPDpWkg8v8/ZJb6jw2NYBDBbpJdN5wCUDH3z94V2hcB8EohBoWcBDGTrThfNFl8VrxV4zpkBg1QTWIGA30LeLbBcO7zti1a1G5SCQCKxFwE5Hc9FUJwSMex8aAmsRcH306FrqdWgckYruRmAtju7JrLyh4zPV+Um7keEuCAQgsBYBO/LoUYm3ww1ZAw7gfJg4nsBaBDyeBE+AQEACCDhgo2EyBDIBBIwvQCAwAQQcuPEwHQIIGB+AQGACCDhw42E6BBAwPgCBwAQQcODGw3QIIGB8AAKBCSDgwI2H6RBAwPgABAITQMCBGw/TIYCA8QEIBCaAgAM3HqZDAAHjAxAITAABB248TIcAAsYHIBCYAAIO3HiYDgEEjA9AIDABBBy48TAdAggYH4BAYAIIOHDjYToEEDA+AIHABBBw4MbDdAggYHwAAoEJIODAjYfpEEDA+AAEAhNAwIEbD9MhgIDxAQgEJoCAAzcepkMAAeMDEAhMAAEHbjxMhwACxgcgEJgAAg7ceJgOAQSMD0AgMAEEHLjxMB0CCBgfgEBgAgg4cONhOgQQMD4AgcAEEHDgxsN0CCBgfAACgQkg4MCNh+kQQMD4AAQCE0DAgRsP0yGAgPEBCAQmgIADNx6mQwAB4wMQCEwAAQduPEyHAALGByAQmAACDtx4mA4BBIwPQCAwAQQcuPEwHQIIGB+AQGACCDhw42E6BBAwPgCBwAQQcODGw3QIfA/dMREe6/EpZgAAAABJRU5ErkJggg==");
                    put("MHRD", "data:image/png;base64,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");
            }

        },
                true);

        workflowRepository.save(w);

        Workflow retw = workflowRepository.findById("5v0").orElse(null);
        return new ResponseEntity<>(retw, HttpStatus.OK);
    }

    @GetMapping(value = "/addTest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<test> addTest() {
        List<Comment> comments = new ArrayList<>(Arrays.asList(new Comment(0,"MHRD", "Sorry rejecting the application", "Jun  6  at  0:13,   2020")));
        test t1 = new test("1", "title", comments, new ArrayList<>(Arrays.asList("1","2")));
        testRepository.save(t1);

        test t = testRepository.findById("1").orElseGet(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @GetMapping(value = "/test/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<test> getTestyId(@PathVariable String id) {
        System.out.println("Test id : " + id);
        try {
            test t = testRepository.findById(id).orElse(null);
            if (t != null) {
                System.out.println("test : " + t.getId());
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(t, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
