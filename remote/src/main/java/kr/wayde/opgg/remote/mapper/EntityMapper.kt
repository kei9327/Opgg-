package kr.wayde.opgg.remote.mapper

interface EntityMapper<in M, out E> {

    fun mapFromRemote(model: M): E

}
