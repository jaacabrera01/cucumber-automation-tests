Feature: The Internet
  This feature covers (some) Example pages on 'the-internet.herokuapp.com'

  @TEST_TI_0001 #verify that expected examples are displayed
  Scenario: Homepage has a list of links to Expected examples
    Given the page under test is 'https://the-internet.herokuapp.com'
    Then the list of examples should contain:
      | A/B Testing                       |
      | Add/Remove Elements               |
      | Basic Auth (user and pass: admin) |
      | Broken Images                     |
      | Challenging DOM                   |
      | Context Menu                      |
      | Digest Authentication (user and pass: admin) |
      | Disappearing Elements             |
      | Drag and Drop                     |
      | Dropdown                          |
      | Dynamic Content                   |
      | Dynamic Controls                  |
      | Dynamic Loading                   |
      | Entry Ad                          |
      | Exit Intent                       |
      | File Download                     |
      | File Upload                       |
      | Floating Menu                     |
      | Forgot Password                   |
      | Form Authentication               |
      | Geolocation                       |
      | Horizontal Slider                 |
      | Infinite Scroll                   |
      | Inputs                            |
      | JavaScript Alerts                 |
      | JavaScript onload event error     |
      | Key Presses                       |
      | Large & Deep DOM                  |
      | Multiple Windows                  |
      | Nested Frames                     |
      | Notification Messages             |
      | Redirect Link                     |
      | Secure File Download              |
      | Shadow DOM                        |
      | Shifting Content                  |
      | Slow Resources                    |
      | Sortable Data Tables              |
      | Status Codes                      |
      | Typos                             |
      | WYSIWYG Editor                    |

  @TEST_TI_0002 #Checking of valid access
  Scenario Outline: Basic Auth allows validated access
    Given the page under test is 'https://the-internet.herokuapp.com'
    When the 'Basic Auth' example is opened with username '<username>' and password '<password>'
    Then '<expected_message>' should be displayed

    Examples:
      | username | password | expected_message                                       |
      | admin    | admin    | Congratulations! You must have the proper credentials. |

  @TEST_TI_0003 # For checking of invalid credentials
  Scenario Outline: Basic Auth invalid access
    Given the page under test is 'https://the-internet.herokuapp.com'
    When a Basic Auth request is sent with username '<username>' and password '<password>'
    Then the Basic Auth response status should be 401
    Examples:
      | username | password |
      | invalid  | invalid  |
      | admin    | wrong    |
      | admin    |          |
      |          |          |
      |          | admin    |

  @TEST_TI_0004 # Verify the data tables
  Scenario: Sortable Data Tables - Example 1 displays the expected 4 results
    Given the page under test is 'https://the-internet.herokuapp.com'
    When the 'Sortable Data Tables' example is opened
    Then the Example 1 table should display:
      | Last Name | First Name | Email                 | Due     | Web Site                 |
      | Smith     | John       | jsmith@gmail.com      | $50.00  | http://www.jsmith.com    |
      | Bach      | Frank      | fbach@yahoo.com       | $51.00  | http://www.frank.com     |
      | Doe       | Jason      | jdoe@hotmail.com      | $100.00 | http://www.jdoe.com      |
      | Conway    | Tim        | tconway@earthlink.net | $50.00  | http://www.timconway.com |

  @TEST_TI_0005
  Scenario Outline: Sortable Data Tables - Example 1 sorts ascending
    Given the page under test is 'https://the-internet.herokuapp.com'
    When the 'Sortable Data Tables' example is opened
    And Example 1 is sorted by '<column>'
    Then Example 1 should be sorted ascending by '<column>'

    Examples:
      | column     |
      | Last Name  |
      | First Name |
      | Email      |
      | Due        |
      | Web Site   |

  @TEST_TI_0006
  Scenario Outline: Sortable Data Tables - Example 1 sorts descending
    Given the page under test is 'https://the-internet.herokuapp.com'
    When the 'Sortable Data Tables' example is opened
    And Example 1 is sorted by '<column>'
    And Example 1 is sorted by '<column>'
    Then Example 1 should be sorted descending by '<column>'

    Examples:
      | column     |
      | Last Name  |
      | First Name |
      | Email      |
      | Due        |
      | Web Site   |

  @TEST_TI_0007
  Scenario Outline: Sortable Data Tables - Example 2 sorts ascending
    Given the page under test is 'https://the-internet.herokuapp.com'
    When the 'Sortable Data Tables' example is opened
    And Example 2 is sorted by '<column>'
    Then Example 2 should be sorted ascending by '<column>'

    Examples:
      | column     |
      | Last Name  |
      | First Name |
      | Email      |
      | Due        |
      | Web Site   |

  @TEST_TI_0008
  Scenario Outline: Sortable Data Tables - Example 2 sorts descending
    Given the page under test is 'https://the-internet.herokuapp.com'
    When the 'Sortable Data Tables' example is opened
    And Example 2 is sorted by '<column>'
    And Example 2 is sorted by '<column>'
    Then Example 2 should be sorted descending by '<column>'

    Examples:
      | column     |
      | Last Name  |
      | First Name |
      | Email      |
      | Due        |
      | Web Site   |
