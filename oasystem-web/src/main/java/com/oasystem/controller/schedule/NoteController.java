package com.oasystem.controller.schedule;

import com.oasystem.ResultDTO;
import com.oasystem.model.Note;
import com.oasystem.model.NoteGroup;
import com.oasystem.service.schedule.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "便签模块")
@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping(value = "/query/{userId}")
    @ApiOperation(value = "便签分组", notes = "查询个人便签分组")
    @ResponseBody
    public ResultDTO<List<NoteGroup>> query(@PathVariable("userId") Integer userId) {
        return noteService.queryNoteGroup(userId);

    }


    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增便签分组", notes = "新增便签分组")
    @ResponseBody
    public ResultDTO<Boolean> insert(@RequestBody NoteGroup noteGroup) {
        return noteService.insertNoteGroup(noteGroup);

    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改便签分组", notes = "修改便签分组")
    @ResponseBody
    public ResultDTO<Boolean> update(@RequestBody NoteGroup noteGroup) {
        return noteService.UpdateNoteGroup(noteGroup);

    }

    @GetMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除便签分组", notes = "删除便签分组")
    @ResponseBody
    public ResultDTO<Boolean> delete(@PathVariable("id") Integer id) {
        return noteService.delteNoteGroup(id);
    }


    @PostMapping(value = "/insertNote")
    @ApiOperation(value = "新增便签", notes = "新增便签")
    @ResponseBody
    public ResultDTO<Boolean> insertNote(@RequestBody Note note) {
        return noteService.addNote(note);

    }

    @PostMapping(value = "/updateNote")
    @ApiOperation(value = "修改便签", notes = "修改便签")
    @ResponseBody
    public ResultDTO<Boolean> updateNote(@RequestBody Note note) {
        return noteService.updateNote(note);

    }

    @PostMapping(value = "/updateNoteDone")
    @ApiOperation(value = "是否完成便签", notes = "是否完成便签")
    @ResponseBody
    public ResultDTO<Boolean> updateNoteDone(@RequestBody List<Note> noteList) {
        return noteService.updateNoteDone(noteList);

    }

    @GetMapping(value = "/deleteNote/{id}")
    @ApiOperation(value = "删除便签", notes = "删除便签")
    @ResponseBody
    public ResultDTO<Boolean> deleteNote(@PathVariable("id") Integer id) {
        return noteService.deleteNote(id);
    }

    @GetMapping(value = "/queryNote/{noteGroupId}/{userId}")
    @ApiOperation(value = "便签查询", notes = "便签查询")
    @ResponseBody
    public ResultDTO<List<Note>> queryNote(@PathVariable("noteGroupId") Integer noteGroupId, @PathVariable("userId") Integer userId) {
        return noteService.queryNoteByNoteGroup(noteGroupId, userId);

    }

}
