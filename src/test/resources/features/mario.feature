Feature: Test the vertx api

  @Service
  Scenario: BDD Test to Orchestrate and authentiate for a given User with bearer passed
    When I run [post] on [/runner/api/user?emailId=sudmaili@mailinator.com]
    Then the response should be "OK" and contain
    """
    {
    "code": 200,
    "message": "success",
    "hasError": false,
    "data": {
    "email": "test@mailinator.com",
    "firstName": "test",
    "lastName": "user"
        }
     }
    """


  @Service
  Scenario: BDD Test to Orchestrate and register for a given User with identity passed
    When I run [post] on [/runner/api/user]
    Then the response should be "OK" and contain
    """
    {
     "code": 200,
    "message": "success",
    "hasError": false,
    "data": {
    "link": "http://localhost:8080/runner/api/link/c9f2f133a5b445368b23c4ff8cf71bef"
        }
    }
    """
