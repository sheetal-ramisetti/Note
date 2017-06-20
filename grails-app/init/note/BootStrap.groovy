package note

import com.user.User
import org.mysecurerest.Authority
import org.mysecurerest.UserAuthority

class BootStrap {

    def init = { servletContext ->


       User userInstance =  new User(firstName: "sheetal", lastName: "ramisetti",email: "ramiset@csu.edu",password:"yeysye", userName: "SheetalRamisetti").save()

         new Note(text: "womething", user: userInstance).save()
         new Note(text: "nothing",user: userInstance).save()

        def role1 = new Authority(authority:"ROLE_USER").save flush:true
//        def user1 = new User(username:"user1",password:"pwd1").save flush:true
        UserAuthority.create(userInstance,role1)
    }
    def destroy = {
    }
}
