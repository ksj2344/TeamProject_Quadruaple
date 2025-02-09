var se=Object.defineProperty;var re=(n,e,t)=>e in n?se(n,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):n[e]=t;var k=(n,e,t)=>re(n,typeof e!="symbol"?e+"":e,t);import{G as M}from"./iconBase-DizHl2PJ.js";import{r as p,o as I,j as v}from"./index-DlYLfxzI.js";function he(n){return M({tag:"svg",attr:{viewBox:"0 0 24 24"},child:[{tag:"path",attr:{fill:"none",d:"M0 0h24v24H0z"},child:[]},{tag:"path",attr:{d:"M16 1H4c-1.1 0-2 .9-2 2v14h2V3h12V1zm3 4H8c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h11c1.1 0 2-.9 2-2V7c0-1.1-.9-2-2-2zm0 16H8V7h11v14z"},child:[]}]})(n)}function ue(n){return M({tag:"svg",attr:{viewBox:"0 0 24 24"},child:[{tag:"path",attr:{fill:"none",d:"M0 0h24v24H0V0z"},child:[]},{tag:"path",attr:{d:"M18 20H4V6h9V4H4c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2v-9h-2v9zm-7.79-3.17-1.96-2.36L5.5 18h11l-3.54-4.71zM20 4V1h-2v3h-3c.01.01 0 2 0 2h3v2.99c.01.01 2 0 2 0V6h3V4h-3z"},child:[]}]})(n)}function pe(n){return M({tag:"svg",attr:{viewBox:"0 0 24 24"},child:[{tag:"path",attr:{fill:"none",d:"M0 0h24v24H0z"},child:[]},{tag:"path",attr:{d:"M14 2H4c-1.1 0-2 .9-2 2v10h2V4h10V2zm4 4H8c-1.1 0-2 .9-2 2v10h2V8h10V6zm2 4h-8c-1.1 0-2 .9-2 2v8c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2v-8c0-1.1-.9-2-2-2zm0 10h-8v-8h8v8z"},child:[]}]})(n)}const m=typeof window<"u"&&typeof document<"u"?p.useLayoutEffect:p.useEffect,o=(n,e,t)=>{m(()=>{if(!n||!t)return;const r=function(){for(var l=arguments.length,h=new Array(l),s=0;s<l;s++)h[s]=arguments[s];return h===void 0?t(n):t(n,...h)};return kakao.maps.event.addListener(n,e,r),()=>{kakao.maps.event.removeListener(n,e,r)}},[n,e,t])},V="__react-kakao-maps-sdk__";let g=function(n){return n[n.INITIALIZED=0]="INITIALIZED",n[n.LOADING=1]="LOADING",n[n.SUCCESS=2]="SUCCESS",n[n.FAILURE=3]="FAILURE",n}({});const oe=`${V}_Loader`,i=class i{constructor(e){k(this,"callbacks",[]);k(this,"done",!1);k(this,"loading",!1);k(this,"errors",[]);let{appkey:t,id:r=oe,libraries:l=[],nonce:h,retries:s=3,url:E="//dapi.kakao.com/v2/maps/sdk.js"}=e;if(this.id=r,this.appkey=t,this.libraries=l,this.nonce=h,this.retries=s,this.url=E,i.instance&&!i.equalOptions(this.options,i.instance.options)&&!i.equalOptions(this.options,i.instance.options))switch(i.instance.status){case g.FAILURE:throw new Error(`Loader must not be called again with different options. 
${JSON.stringify(this.options,null,2)}
!==
${JSON.stringify(i.instance.options,null,2)}`);default:i.instance.reset(),i.instance=this;break}return i.instance||(i.instance=this),i.instance}get options(){return{appkey:this.appkey,id:this.id,libraries:this.libraries,nonce:this.nonce,retries:this.retries,url:this.url}}static addLoadEventLisnter(e){return window.kakao&&window.kakao.maps&&window.kakao.maps.load(e),i.loadEventCallback.add(e),e}static removeLoadEventLisnter(e){return i.loadEventCallback.delete(e)}load(){return new Promise((e,t)=>{this.loadCallback(r=>{r?t(r):e(window.kakao)})})}get status(){return this.onEvent?g.FAILURE:this.done?g.SUCCESS:this.loading?g.LOADING:g.INITIALIZED}get failed(){return this.done&&!this.loading&&this.errors.length>=this.retries+1}loadCallback(e){this.callbacks.push(e),this.execute()}resetIfRetryingFailed(){this.failed&&this.reset()}reset(){this.deleteScript(),this.done=!0,this.loading=!1,this.errors=[],this.onEvent=void 0}execute(){if(this.resetIfRetryingFailed(),this.done)this.callback();else{if(window.kakao&&window.kakao.maps){console.warn("Kakao Maps이 이미 외부 요소에 의해 로딩되어 있습니다.설정한 옵션과 일치 하지 않을 수 있으며, 이에 따른 예상치 동작이 발생할 수 있습니다."),window.kakao.maps.load(this.callback);return}this.loading||(this.loading=!0,this.setScript())}}setScript(){document.getElementById(this.id)&&this.callback();const e=this.createUrl(),t=document.createElement("script");t.id=this.id,t.type="text/javascript",t.src=e,t.onerror=this.loadErrorCallback.bind(this),t.onload=this.callback.bind(this),t.defer=!0,t.async=!0,this.nonce&&(t.nonce=this.nonce),document.head.appendChild(t)}loadErrorCallback(e){if(this.errors.push(e),this.errors.length<=this.retries){const t=this.errors.length*2**this.errors.length;console.log(`Failed to load Kakao Maps script, retrying in ${t} ms.`),setTimeout(()=>{this.deleteScript(),this.setScript()},t)}else this.done=!0,this.loading=!1,this.onEvent=this.errors[this.errors.length-1],this.callbacks.forEach(t=>{t(this.onEvent)}),this.callbacks=[],i.loadEventCallback.forEach(t=>{t(this.onEvent)})}createUrl(){let e=this.url;return e+=`?appkey=${this.appkey}`,this.libraries.length&&(e+=`&libraries=${this.libraries.join(",")}`),e+="&autoload=false",e}deleteScript(){const e=document.getElementById(this.id);e&&e.remove()}callback(){kakao.maps.load(()=>{i.instance.done=!0,i.instance.loading=!1,i.instance.callbacks.forEach(e=>{e(i.instance.onEvent)}),i.instance.callbacks=[],i.loadEventCallback.forEach(e=>{e(i.instance.onEvent)})})}static equalOptions(e,t){if(e.appkey!==t.appkey||e.id!==t.id||e.libraries.length!==t.libraries.length)return!1;for(let r=0;r<e.libraries.length;++r)if(e.libraries[r]!==t.libraries[r])return!1;return!(e.nonce!==t.nonce||e.retries!==t.retries||e.url!==t.url)}};k(i,"loadEventCallback",new Set);let C=i;const u=function(n,e){for(var t=arguments.length,r=new Array(t>2?t-2:0),l=2;l<t;l++)r[l-2]=arguments[l];m(()=>{!n||r.every(h=>typeof h>"u")||n[e].call(n,...r)},[n,e,...r])},H=I.createContext(void 0),fe=I.forwardRef(function(e,t){let{id:r,as:l,children:h,center:s,isPanto:E=!1,padding:R=32,disableDoubleClick:L,disableDoubleClickZoom:y,draggable:x,zoomable:U,keyboardShortcuts:A,level:S,maxLevel:j,minLevel:O,mapTypeId:f,projectionId:z,scrollwheel:F,tileAnimation:D,onBoundsChanged:K,onCenterChanged:N,onClick:$,onDoubleClick:Z,onDrag:B,onDragEnd:G,onDragStart:P,onIdle:q,onMaptypeidChanged:J,onMouseMove:Q,onRightClick:W,onTileLoaded:X,onZoomChanged:Y,onZoomStart:_,onCreate:b,...ee}=e;const te=l||"div",[w,ne]=p.useState(!1),[a,ae]=p.useState(),T=p.useRef(null);return m(()=>{const c=C.addLoadEventLisnter(d=>ne(!d));return()=>{C.removeLoadEventLisnter(c)}},[]),m(()=>{if(!w)return;const c=T.current;if(!c)return;const d="lat"in s?new kakao.maps.LatLng(s.lat,s.lng):new kakao.maps.Coords(s.x,s.y),ie=new kakao.maps.Map(c,{center:d,disableDoubleClick:L,disableDoubleClickZoom:y,draggable:x,keyboardShortcuts:A,level:S,mapTypeId:typeof f=="string"?kakao.maps.MapTypeId[f]:f,projectionId:z,scrollwheel:F,tileAnimation:D});return ae(ie),()=>{c.innerHTML=""}},[w,L,y,D]),p.useImperativeHandle(t,()=>a,[a]),m(()=>{!a||!b||b(a)},[a,b]),m(()=>{if(!a)return;let c=a.getCenter();c instanceof kakao.maps.Coords&&(c=c.toLatLng());const d="lat"in s?new kakao.maps.LatLng(s.lat,s.lng):new kakao.maps.Coords(s.x,s.y);d instanceof kakao.maps.LatLng&&d.equals(c)||d instanceof kakao.maps.Coords&&d.toLatLng().equals(c)||(E?a.panTo(d,R):a.setCenter(d))},[a,s.lat,s.lng,s.x,s.y]),u(a,"setDraggable",x),u(a,"setZoomable",U),u(a,"setKeyboardShortcuts",A),u(a,"setLevel",S),u(a,"setMapTypeId",w?typeof f=="string"?kakao.maps.MapTypeId[f]:f:void 0),u(a,"setProjectionId",z),u(a,"setMinLevel",j),u(a,"setMaxLevel",O),o(a,"bounds_changed",K),o(a,"center_changed",N),o(a,"click",$),o(a,"dblclick",Z),o(a,"drag",B),o(a,"dragstart",P),o(a,"dragend",G),o(a,"idle",q),o(a,"maptypeid_changed",J),o(a,"mousemove",Q),o(a,"rightclick",W),o(a,"tilesloaded",X),o(a,"zoom_changed",Y),o(a,"zoom_start",_),v.jsxs(v.Fragment,{children:[v.jsx(te,{id:r||`${V}_Map`,...ee,ref:T}),a&&v.jsx(H.Provider,{value:a,children:h})]})}),ke=n=>{const e=p.useContext(H);if(!e)throw new Error(`${n?n+" Component":"useMap"} must exist inside Map Component!`);return e},me=I.createContext(void 0);export{me as K,he as M,ue as a,u as b,o as c,fe as d,pe as e,ke as u};
