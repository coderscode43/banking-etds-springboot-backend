angular.module("ng.httpLoader",["ng.httpLoader.httpMethodInterceptor"]).directive("ngHttpLoader",["$rootScope","$parse","$timeout",function(a,c,b){return{scope:{methods:"@",template:"@",title:"@",ttl:"@"},template:'<div class="http-loader__wrapper" ng-include="template" ng-show="showLoader"></div>',link:function(g){var f=c(g.methods)()||g.methods;
f=angular.isUndefined(f)?[]:f;
f=angular.isArray(f)?f:[f];
angular.forEach(f,function(k,j){f[j]=k.toUpperCase()
});
var d=c(g.ttl)()||g.ttl;
d=angular.isUndefined(d)?0:d;
d=Number(d)*1000;
d=angular.isNumber(d)?d:0;
if(!Array.prototype.indexOf){f.indexOf=function(k){for(var j=this.length;
j--;
){if(this[j]===k){return j
}}return -1
}
}g.showLoader=false;
var i,h=g.showLoader;
var e=function(j,k){if(f.indexOf(k.toUpperCase())!==-1){h=(j.name==="loaderShow")
}else{if(f.length===0){h=(j.name==="loaderShow")
}}if(d<=0||(!i&&!h)){g.showLoader=h;
return
}if(i){return
}g.showLoader=h;
i=b(function(){if(!h){g.showLoader=h
}i=undefined
},d)
};
a.$on("loaderShow",e);
a.$on("loaderHide",e)
}}
}]);
angular.module("ng.httpLoader.httpMethodInterceptor",[]).provider("httpMethodInterceptor",function(){var a=[],b=false;
this.whitelistDomain=function(c){a.push(c)
};
this.whitelistLocalRequests=function(){b=true
};
this.$get=["$q","$rootScope",function(d,c){var f=0;
var e=function(h){if(h.substring(0,2)!=="//"&&h.indexOf("://")===-1&&b){return true
}for(var j=a.length;
j--;
){if(h.indexOf(a[j])!==-1){return true
}}return false
};
var g=function(h){if(e(h.url)&&(--f)===0){c.$emit("loaderHide",h.method)
}};
return{request:function(h){if(e(h.url)){f++;
c.$emit("loaderShow",h.method)
}return h||d.when(h)
},response:function(h){g(h.config);
return h||d.when(h)
},responseError:function(h){g(h.config);
return d.reject(h)
}}
}]
}).config(["$httpProvider",function(a){a.interceptors.unshift("httpMethodInterceptor")
}]);