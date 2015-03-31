﻿(function (module) {

  var addToken = function (currentUser, $q, $log) {

    return {
      request: function (config) {
        if (currentUser.profile.authorizationEncoded) {
          //$log.debug("Adding authorization to header " + "Basic " + currentUser.profile.authorizationEncoded);
          config.headers.Authorization = "Basic " + currentUser.profile.authorizationEncoded;
        }
        return $q.when(config);
      }
    };
  };
  addToken.$inject = ["currentUser", "$q", "$log"];

  module.factory("addToken", addToken);
  module.config(["$httpProvider", function ($httpProvider) {
    $httpProvider.interceptors.push("addToken");
  }]);

})(angular.module("civApp"));
