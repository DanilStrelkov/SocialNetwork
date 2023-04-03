package ru.skillbox.zerone.backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.zerone.backend.model.dto.request.DialogRequestDTO;
import ru.skillbox.zerone.backend.model.dto.request.MessageRequestDTO;
import ru.skillbox.zerone.backend.model.dto.response.*;
import ru.skillbox.zerone.backend.service.DialogService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dialogs")
public class DialogsController {
  private final DialogService dialogService;

  @GetMapping("/unreaded")
  public CommonResponseDTO<CountDTO> getUnreadedMessages() {
    return dialogService.getUnreaded();
  }

  @PostMapping("/{id}/messages")
  public CommonResponseDTO<MessageDataDTO> postMessages(@PathVariable long id,
                                                        @RequestBody MessageRequestDTO messageRequestDTO) {
    return dialogService.postMessages(id, messageRequestDTO);
  }

  @GetMapping("/{id}/messages")
  public CommonListResponseDTO<MessageDataDTO> getMessages(@PathVariable long id,
                                                           @RequestParam(name = "offset", defaultValue = "0") @Min(0) int offset,
                                                           @RequestParam(name = "itemPerPage", defaultValue = "1000") @Min(0) int itemPerPage) {
    return dialogService.getMessages(id, offset, itemPerPage);
  }

  @PostMapping
  public CommonResponseDTO<DialogDataDTO> postDialogs(@Valid @RequestBody DialogRequestDTO dialogRequestDTO) {
    return dialogService.postDialogs(dialogRequestDTO);
  }

  @GetMapping
  public CommonListResponseDTO<DialogDataDTO> getDialogs(@RequestParam(name = "offset", defaultValue = "0") @Min(0) int offset,
                                                         @RequestParam(name = "itemPerPage", defaultValue = "1000") @Min(0) int itemPerPage) {
    return dialogService.getDialogs(offset, itemPerPage);
  }
}