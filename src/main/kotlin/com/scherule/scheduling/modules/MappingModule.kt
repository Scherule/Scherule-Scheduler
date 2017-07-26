package com.scherule.scheduling.modules

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.google.inject.AbstractModule

class MappingModule : AbstractModule() {

    override fun configure() {
        bindObjectMapper()
    }

    private fun bindObjectMapper() {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JodaModule())
        bind(ObjectMapper::class.java).toInstance(objectMapper)
    }

}