package com.winfred.core.entity.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfo {
  private String userId;
  private String userName;
  private Boolean isLogin;
  private List<UserRole> userRoles;
}
