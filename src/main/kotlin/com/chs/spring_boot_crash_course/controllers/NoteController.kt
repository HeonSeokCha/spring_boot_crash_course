package com.chs.spring_boot_crash_course.controllers

import com.chs.spring_boot_crash_course.controllers.request.RequestNote
import com.chs.spring_boot_crash_course.controllers.response.ResponseNote
import com.chs.spring_boot_crash_course.database.model.Note
import com.chs.spring_boot_crash_course.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*
import java.time.Instant


@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
) {

    @PostMapping
    fun save(
        @RequestBody body: RequestNote
    ): ResponseNote {
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createTime = Instant.now(),
                ownerId = ObjectId()
            )
        )

        return note.toResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String
    ): List<ResponseNote> {
        return repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toResponse()
        }
    }
}
