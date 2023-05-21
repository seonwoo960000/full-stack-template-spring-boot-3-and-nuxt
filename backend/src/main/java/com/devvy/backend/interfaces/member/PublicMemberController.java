package com.devvy.backend.interfaces.member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devvy.backend.application.facade.MemberManagementService;
import com.devvy.backend.application.impl.MemberManagementServiceImpl;
import com.devvy.backend.common.enums.AuthorizationType;
import com.devvy.backend.interfaces.error.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/public/members")
public class PublicMemberController {

    private final MemberManagementService memberManagementService;
    private final MemberMapper memberMapper;

    public PublicMemberController(MemberManagementServiceImpl memberManagementService, MemberMapper memberMapper) {
        this.memberManagementService = memberManagementService;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find member by id"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Ok",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = MemberResponse.class),
                        examples = {
                            @ExampleObject(
                                name = "MemberResponse",
                                value = """
                                    {
                                      "login": "devvy@gmail.com",
                                      "firstName": "Seon Woo",
                                      "lastName": "Kim",
                                      "email": "devvy@gmail.com",
                                      "langKey": "EN",
                                      "imageUrl": "http://your-image-url.co"
                                    }
                                    """
                            )
                        }
                    )
                }
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Account Not Found",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class),
                        examples = {
                            @ExampleObject(
                                name = "ErrorResponse",
                                value = """
                                    {
                                      "request": "ServletWebRequest: uri=/api/v1/member/5950863e-d40f-11ed-a61b-7f379855e063;client=0:0:0:0:0:0:0:1",
                                      "message": "Member not found"
                                    }
                                    """
                            )
                        }
                    )
                }
            )
        }
    )
    MemberResponse findById(
        @Schema(example = "5950863e-d40f-11ed-a61b-7f379855e063")
        @PathVariable UUID id
    ) {
        var memberDto = memberManagementService.findById(id);
        return memberMapper.fromMemberDtoToMemberResponse(memberDto);
    }

    @GetMapping("/name/{name}")
    MemberResponse findByName(
        @Schema(example = "devvy@gmail.com")
        @PathVariable String name,
        @Schema(example = "NONE")
        @RequestParam(required = false) Optional<AuthorizationType> authorizationType
    ) {
        var memberDto = memberManagementService.findByName(
            name,
            authorizationType.orElse(AuthorizationType.NONE)
        );
        return memberMapper.fromMemberDtoToMemberResponse(memberDto);
    }

    @PostMapping
    @Operation(
        summary = "Register member"
    )
    MemberRegisterResponse registerMember(
        @RequestBody MemberRegisterRequest memberRegisterRequest
    ) {
        var memberDto = memberManagementService.registerMember(
            memberMapper.fromMemberRegisterRequestToMemberDto(memberRegisterRequest));
        return memberMapper.fromMemberDtoToMemberRegisterResponse(memberDto);
    }
}
