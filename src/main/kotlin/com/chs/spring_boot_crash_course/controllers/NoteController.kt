package com.chs.spring_boot_crash_course.controllers

import com.chs.spring_boot_crash_course.controllers.request.RequestNote
import com.chs.spring_boot_crash_course.controllers.response.ResponseNote
import com.chs.spring_boot_crash_course.database.model.Note
import com.chs.spring_boot_crash_course.repository.NoteRepository
import jakarta.validation.Valid
import org.bson.types.ObjectId
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.Instant


@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
) {

    @PostMapping
    fun save(
        @Valid @RequestBody body: RequestNote
    ): ResponseNote {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createTime = Instant.now(),
                ownerId = ObjectId(ownerId)
            )
        )

        return note.toResponse()
    }

    @GetMapping
    fun findByOwnerId(): List<ResponseNote> {
        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        return repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toResponse()
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id: String) {
        val note = repository.findById(ObjectId(id)).orElseThrow {
            IllegalArgumentException("Note not found")
        }

        val ownerId = SecurityContextHolder.getContext().authentication.principal as String
        if (note.ownerId.toHexString() == ownerId) {
            repository.deleteById(ObjectId(id))
        }
    }
}
