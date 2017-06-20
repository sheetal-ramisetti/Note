package com.user


/**
 * Created by sramisetti on 6/9/2017.
 */
class UserService {

            User createService(Map map){

                User user = new User()
                user.firstName = map.firstName
                user.lastName = map.lastName
                user.email = map.email
                user.password = map.password
                        user.validate()
                        if(user.hasErrors()){
                            user.discard()
                        }else {
                            user.save()
                        }
                return user
            }




        List listService(){

                List users = User.list()
                return users
        }

        User updateService(Map map, Integer id){
            User user = User.findById(id)

            user.properties = map
            if(user.hasErrors()){
                user.discard()
            }else {
                user.save(flush: true)
            }
            return user

        }
        void deleteService(Integer id){

            User user = User.findById(id)
            user.delete(flush: true)
        }

        User getByIdService(Integer id){
            User user = User.findById(id)
            return user
        }


}
