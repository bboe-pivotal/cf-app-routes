# Cloud Foundry Application Routes Tool
Simple tool for displaying the routing table within Cloud Foundry for debugging purposes.

Installation instructions
1. Log on to Pivotal Ops Mgr to determine the IP address and admin password for the router



1. Create user provided service with credentials to back-end router service:
  ```
  cf create-user-provided-service route-credentials -p '{
    "routepath":"http://<router-address>:8080/routes",
    "username":"router_status",
    "password":"<password>"
  }'
  ```
