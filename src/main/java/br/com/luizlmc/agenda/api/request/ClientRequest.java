package br.com.luizlmc.agenda.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientRequest(@NotBlank String name, @NotBlank @Email String email, @Size(max = 15) String phone) {
}

