Feature: feature to test login functionality
  Scenario Outline: Check login with valid credentials
    Given browser dibuka
    And pengguna dalam hlm register
    And pengguna ke hlm login
    When pengguna masukan <namaakun> and <sandi>
    And tombol login diklik
    Then pengguna redirect ke hlm utama
    Examples:
    |namaakun              |sandi      |
    |susiana@gmail.com     |TI190440   |
    |susiana@gmail.com     |           |
    |                      |TI190440   |
    |                      |           |
    |susiana@gmail.com     |TI0440     |
    |susiana@gmail.com     |TI71190444444444000|
    |susiana@gmail.com     |TI190440_?/|

