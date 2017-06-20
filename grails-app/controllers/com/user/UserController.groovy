package com.user

import grails.converters.JSON
import org.springframework.context.MessageSource

/**
 * Created by sramisetti on 6/9/2017.
 */
class UserController {

     UserService userService
     MessageSource messageSource
     def create() {
        Map map = request.JSON
        GUser user = userService.createService(map)
        if (user.hasErrors()) {
            List errors = []
            user.errors.allErrors.each
                    {error->
                        errors << messageSource.getMessage(error,Locale.default)}
            render errors as JSON
        }
        else{
            render user as JSON
        }
    }
   // @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def list(){

          List users = userService.listService()
          render users as JSON
    }
    def update() {
        Map map = request.JSON
        Integer id = params.int('userId')
        GUser user = userService.updateService(map, id)
        if(user.hasErrors()){
            List errors = []
            user.errors.allErrors.each {error->
                errors << messageSource.getMessage(error,Locale.default)
            }
            render errors as JSON
        }

        render user as JSON
    }


    def delete(){
        Integer id = params.int("userId")
        userService.deleteService(id)
        render "[]"

    }


    def getById(){
        Integer id = params.int('userId')
        GUser user = userService.getByIdService(id)
        render user as JSON
    }






}
