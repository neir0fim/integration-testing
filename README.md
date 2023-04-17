## Integration tests
you need docker installed and running for the tests to work

## Start application with keycloak
* run [docker-compose.yml](docker%2Fdocker-compose.yml) (docker must be running)
* go to http://localhost:8024/auth/admin/master/console/
* credentials for auth username - keycloak; password - keycloak
![Screenshot 2023-04-17 at 15.42.54.png](resources%2FScreenshot%202023-04-17%20at%2015.42.54.png)
* all realms will be imported automatically, you need to create a user
* go to users tab
![Screenshot 2023-04-17 at 15.46.28.png](resources%2FScreenshot%202023-04-17%20at%2015.46.28.png)
* add new user
![Screenshot 2023-04-17 at 15.47.40.png](resources%2FScreenshot%202023-04-17%20at%2015.47.40.png)
* specify username (only one required field) and save
![Screenshot 2023-04-17 at 15.48.48.png](resources%2FScreenshot%202023-04-17%20at%2015.48.48.png)
* go to Credentials tab on user profile page
![Screenshot 2023-04-17 at 15.49.56.png](resources%2FScreenshot%202023-04-17%20at%2015.49.56.png)
* set new password, off temporary option and save
![Screenshot 2023-04-17 at 15.50.58.png](resources%2FScreenshot%202023-04-17%20at%2015.50.58.png)
* now can make post request to obtain token
![Screenshot 2023-04-17 at 15.54.22.png](resources%2FScreenshot%202023-04-17%20at%2015.54.22.png)
* copy access_token and paste as token to authorization
![Screenshot 2023-04-17 at 15.57.22.png](resources%2FScreenshot%202023-04-17%20at%2015.57.22.png)