package org.mysecurerest

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UserAuthority implements Serializable {

	private static final long serialVersionUID = 1

	User user
	Authority authority

	UserAuthority(User u, Authority r) {
		this()
		user = u
		authority = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof UserAuthority)) {
			return false
		}

		other.user?.id == user?.id && other.authority?.id == authority?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (authority) builder.append(authority.id)
		builder.toHashCode()
	}

	static UserAuthority get(long userId, long authorityId) {
		criteriaFor(userId, authorityId).get()
	}

	static boolean exists(long userId, long authorityId) {
		criteriaFor(userId, authorityId).count()
	}

	private static DetachedCriteria criteriaFor(long userId, long authorityId) {
		UserAuthority.where {
			user == User.load(userId) &&
			authority == Authority.load(authorityId)
		}
	}

	static UserAuthority create(User user, Authority authority, boolean flush = false) {
		def instance = new UserAuthority(user: user, authority: authority)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(User u, Authority r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UserAuthority.where { user == u && authority == r }.deleteAll()

		if (flush) { UserAuthority.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(User u, boolean flush = false) {
		if (u == null) return

		UserAuthority.where { user == u }.deleteAll()

		if (flush) { UserAuthority.withSession { it.flush() } }
	}

	static void removeAll(Authority r, boolean flush = false) {
		if (r == null) return

		UserAuthority.where { authority == r }.deleteAll()

		if (flush) { UserAuthority.withSession { it.flush() } }
	}

	static constraints = {
		authority validator: { Authority r, UserAuthority ur ->
			if (ur.user == null || ur.user.id == null) return
			boolean existing = false
			UserAuthority.withNewSession {
				existing = UserAuthority.exists(ur.user.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['user', 'authority']
		version false
	}
}
