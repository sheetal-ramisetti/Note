package com.user


/**
 * Created by sramisetti on 6/9/2017.
 */
class UserService {

            GUser createService(Map map){

                GUser user = new GUser()
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

                List users = GUser.list()
                return users
        }

        GUser updateService(Map map, Integer id){
            GUser user = GUser.findById(id)

            user.properties = map
            if(user.hasErrors()){
                user.discard()
            }else {
                user.save(flush: true)
            }
            return user

        }
        void deleteService(Integer id){

            GUser user = GUser.findById(id)
            user.delete(flush: true)
        }

        GUser getByIdService(Integer id){
            GUser user = GUser.findById(id)
            return user
        }


}
