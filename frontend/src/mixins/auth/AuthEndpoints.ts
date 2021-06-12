import axios from "axios";
import {RegisterDto} from "@/mixins/auth/dto/register.dto";
import {VerifyPasswordDto} from "@/mixins/auth/dto/verify.password.dto";
import {ResetPasswordDto} from "@/mixins/auth/dto/reset.password.dto";
import {LoginResponseDto} from "@/mixins/auth/dto/login.response.dto";
import store from '@/store';

export default {
    methods: {
        async checkUser(username: string): Promise<LoginResponseDto> {
            const answer = await axios.post<LoginResponseDto>(process.env.VUE_APP_BACKEND + "auth/check-username", username, {
                headers: {
                    'Content-Type': 'text/plain'
                }
            });
            return answer.data;
        },
        async register(registerDto: RegisterDto): Promise<string> {
            const answer = await axios.post<string>(process.env.VUE_APP_BACKEND + "auth/register", registerDto, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            return answer.data;
        },
        async verifyPassword(verifyPasswordDto: VerifyPasswordDto): Promise<string> {
            return (await axios.post<string>(process.env.VUE_APP_BACKEND + "auth/verify-password", verifyPasswordDto, {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })
            ).data;
        },
        async resetPassword(resetPasswordDto: ResetPasswordDto): Promise<boolean> {
            return (await axios.put<boolean>(process.env.VUE_APP_BACKEND + "auth/reset-password", resetPasswordDto, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': store.getters.getJwt,
                    }
                })
            ).data;
        }
    }
}
