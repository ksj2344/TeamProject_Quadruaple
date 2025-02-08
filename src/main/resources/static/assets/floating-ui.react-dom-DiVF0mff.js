import{r as E,x as _t}from"./index-DIxnD1JS.js";function ft(){return typeof window<"u"}function Q(t){return Pt(t)?(t.nodeName||"").toLowerCase():"#document"}function $(t){var e;return(t==null||(e=t.ownerDocument)==null?void 0:e.defaultView)||window}function z(t){var e;return(e=(Pt(t)?t.ownerDocument:t.document)||window.document)==null?void 0:e.documentElement}function Pt(t){return ft()?t instanceof Node||t instanceof $(t).Node:!1}function N(t){return ft()?t instanceof Element||t instanceof $(t).Element:!1}function _(t){return ft()?t instanceof HTMLElement||t instanceof $(t).HTMLElement:!1}function Ot(t){return!ft()||typeof ShadowRoot>"u"?!1:t instanceof ShadowRoot||t instanceof $(t).ShadowRoot}function nt(t){const{overflow:e,overflowX:n,overflowY:o,display:i}=V(t);return/auto|scroll|overlay|hidden|clip/.test(e+o+n)&&!["inline","contents"].includes(i)}function zt(t){return["table","td","th"].includes(Q(t))}function at(t){return[":popover-open",":modal"].some(e=>{try{return t.matches(e)}catch{return!1}})}function xt(t){const e=yt(),n=N(t)?V(t):t;return["transform","translate","scale","rotate","perspective"].some(o=>n[o]?n[o]!=="none":!1)||(n.containerType?n.containerType!=="normal":!1)||!e&&(n.backdropFilter?n.backdropFilter!=="none":!1)||!e&&(n.filter?n.filter!=="none":!1)||["transform","translate","scale","rotate","perspective","filter"].some(o=>(n.willChange||"").includes(o))||["paint","layout","strict","content"].some(o=>(n.contain||"").includes(o))}function jt(t){let e=Y(t);for(;_(e)&&!J(e);){if(xt(e))return e;if(at(e))return null;e=Y(e)}return null}function yt(){return typeof CSS>"u"||!CSS.supports?!1:CSS.supports("-webkit-backdrop-filter","none")}function J(t){return["html","body","#document"].includes(Q(t))}function V(t){return $(t).getComputedStyle(t)}function ut(t){return N(t)?{scrollLeft:t.scrollLeft,scrollTop:t.scrollTop}:{scrollLeft:t.scrollX,scrollTop:t.scrollY}}function Y(t){if(Q(t)==="html")return t;const e=t.assignedSlot||t.parentNode||Ot(t)&&t.host||z(t);return Ot(e)?e.host:e}function Tt(t){const e=Y(t);return J(e)?t.ownerDocument?t.ownerDocument.body:t.body:_(e)&&nt(e)?e:Tt(e)}function et(t,e,n){var o;e===void 0&&(e=[]),n===void 0&&(n=!0);const i=Tt(t),r=i===((o=t.ownerDocument)==null?void 0:o.body),s=$(i);if(r){const c=ht(s);return e.concat(s,s.visualViewport||[],nt(i)?i:[],c&&n?et(c):[])}return e.concat(i,et(i,[],n))}function ht(t){return t.parent&&Object.getPrototypeOf(t.parent)?t.frameElement:null}const q=Math.min,F=Math.max,rt=Math.round,ot=Math.floor,H=t=>({x:t,y:t}),It={left:"right",right:"left",bottom:"top",top:"bottom"},Yt={start:"end",end:"start"};function pt(t,e,n){return F(t,q(e,n))}function Z(t,e){return typeof t=="function"?t(e):t}function X(t){return t.split("-")[0]}function tt(t){return t.split("-")[1]}function Mt(t){return t==="x"?"y":"x"}function bt(t){return t==="y"?"height":"width"}function U(t){return["top","bottom"].includes(X(t))?"y":"x"}function Rt(t){return Mt(U(t))}function qt(t,e,n){n===void 0&&(n=!1);const o=tt(t),i=Rt(t),r=bt(i);let s=i==="x"?o===(n?"end":"start")?"right":"left":o==="start"?"bottom":"top";return e.reference[r]>e.floating[r]&&(s=st(s)),[s,st(s)]}function Xt(t){const e=st(t);return[wt(t),e,wt(e)]}function wt(t){return t.replace(/start|end/g,e=>Yt[e])}function Ut(t,e,n){const o=["left","right"],i=["right","left"],r=["top","bottom"],s=["bottom","top"];switch(t){case"top":case"bottom":return n?e?i:o:e?o:i;case"left":case"right":return e?r:s;default:return[]}}function Kt(t,e,n,o){const i=tt(t);let r=Ut(X(t),n==="start",o);return i&&(r=r.map(s=>s+"-"+i),e&&(r=r.concat(r.map(wt)))),r}function st(t){return t.replace(/left|right|bottom|top/g,e=>It[e])}function Gt(t){return{top:0,right:0,bottom:0,left:0,...t}}function kt(t){return typeof t!="number"?Gt(t):{top:t,right:t,bottom:t,left:t}}function ct(t){const{x:e,y:n,width:o,height:i}=t;return{width:o,height:i,top:n,left:e,right:e+o,bottom:n+i,x:e,y:n}}function Ct(t,e,n){let{reference:o,floating:i}=t;const r=U(e),s=Rt(e),c=bt(s),f=X(e),l=r==="y",a=o.x+o.width/2-i.width/2,d=o.y+o.height/2-i.height/2,m=o[c]/2-i[c]/2;let u;switch(f){case"top":u={x:a,y:o.y-i.height};break;case"bottom":u={x:a,y:o.y+o.height};break;case"right":u={x:o.x+o.width,y:d};break;case"left":u={x:o.x-i.width,y:d};break;default:u={x:o.x,y:o.y}}switch(tt(e)){case"start":u[s]-=m*(n&&l?-1:1);break;case"end":u[s]+=m*(n&&l?-1:1);break}return u}const Jt=async(t,e,n)=>{const{placement:o="bottom",strategy:i="absolute",middleware:r=[],platform:s}=n,c=r.filter(Boolean),f=await(s.isRTL==null?void 0:s.isRTL(e));let l=await s.getElementRects({reference:t,floating:e,strategy:i}),{x:a,y:d}=Ct(l,o,f),m=o,u={},g=0;for(let h=0;h<c.length;h++){const{name:w,fn:p}=c[h],{x,y:b,data:R,reset:y}=await p({x:a,y:d,initialPlacement:o,placement:m,strategy:i,middlewareData:u,rects:l,platform:s,elements:{reference:t,floating:e}});a=x??a,d=b??d,u={...u,[w]:{...u[w],...R}},y&&g<=50&&(g++,typeof y=="object"&&(y.placement&&(m=y.placement),y.rects&&(l=y.rects===!0?await s.getElementRects({reference:t,floating:e,strategy:i}):y.rects),{x:a,y:d}=Ct(l,m,f)),h=-1)}return{x:a,y:d,placement:m,strategy:i,middlewareData:u}};async function dt(t,e){var n;e===void 0&&(e={});const{x:o,y:i,platform:r,rects:s,elements:c,strategy:f}=t,{boundary:l="clippingAncestors",rootBoundary:a="viewport",elementContext:d="floating",altBoundary:m=!1,padding:u=0}=Z(e,t),g=kt(u),w=c[m?d==="floating"?"reference":"floating":d],p=ct(await r.getClippingRect({element:(n=await(r.isElement==null?void 0:r.isElement(w)))==null||n?w:w.contextElement||await(r.getDocumentElement==null?void 0:r.getDocumentElement(c.floating)),boundary:l,rootBoundary:a,strategy:f})),x=d==="floating"?{x:o,y:i,width:s.floating.width,height:s.floating.height}:s.reference,b=await(r.getOffsetParent==null?void 0:r.getOffsetParent(c.floating)),R=await(r.isElement==null?void 0:r.isElement(b))?await(r.getScale==null?void 0:r.getScale(b))||{x:1,y:1}:{x:1,y:1},y=ct(r.convertOffsetParentRelativeRectToViewportRelativeRect?await r.convertOffsetParentRelativeRectToViewportRelativeRect({elements:c,rect:x,offsetParent:b,strategy:f}):x);return{top:(p.top-y.top+g.top)/R.y,bottom:(y.bottom-p.bottom+g.bottom)/R.y,left:(p.left-y.left+g.left)/R.x,right:(y.right-p.right+g.right)/R.x}}const Qt=t=>({name:"arrow",options:t,async fn(e){const{x:n,y:o,placement:i,rects:r,platform:s,elements:c,middlewareData:f}=e,{element:l,padding:a=0}=Z(t,e)||{};if(l==null)return{};const d=kt(a),m={x:n,y:o},u=Rt(i),g=bt(u),h=await s.getDimensions(l),w=u==="y",p=w?"top":"left",x=w?"bottom":"right",b=w?"clientHeight":"clientWidth",R=r.reference[g]+r.reference[u]-m[u]-r.floating[g],y=m[u]-r.reference[u],C=await(s.getOffsetParent==null?void 0:s.getOffsetParent(l));let A=C?C[b]:0;(!A||!await(s.isElement==null?void 0:s.isElement(C)))&&(A=c.floating[b]||r.floating[g]);const S=R/2-y/2,W=A/2-h[g]/2-1,L=q(d[p],W),B=q(d[x],W),T=L,P=A-h[g]-B,O=A/2-h[g]/2+S,j=pt(T,O,P),D=!f.arrow&&tt(i)!=null&&O!==j&&r.reference[g]/2-(O<T?L:B)-h[g]/2<0,M=D?O<T?O-T:O-P:0;return{[u]:m[u]+M,data:{[u]:j,centerOffset:O-j-M,...D&&{alignmentOffset:M}},reset:D}}}),Zt=function(t){return t===void 0&&(t={}),{name:"flip",options:t,async fn(e){var n,o;const{placement:i,middlewareData:r,rects:s,initialPlacement:c,platform:f,elements:l}=e,{mainAxis:a=!0,crossAxis:d=!0,fallbackPlacements:m,fallbackStrategy:u="bestFit",fallbackAxisSideDirection:g="none",flipAlignment:h=!0,...w}=Z(t,e);if((n=r.arrow)!=null&&n.alignmentOffset)return{};const p=X(i),x=U(c),b=X(c)===c,R=await(f.isRTL==null?void 0:f.isRTL(l.floating)),y=m||(b||!h?[st(c)]:Xt(c)),C=g!=="none";!m&&C&&y.push(...Kt(c,h,g,R));const A=[c,...y],S=await dt(e,w),W=[];let L=((o=r.flip)==null?void 0:o.overflows)||[];if(a&&W.push(S[p]),d){const O=qt(i,s,R);W.push(S[O[0]],S[O[1]])}if(L=[...L,{placement:i,overflows:W}],!W.every(O=>O<=0)){var B,T;const O=(((B=r.flip)==null?void 0:B.index)||0)+1,j=A[O];if(j)return{data:{index:O,overflows:L},reset:{placement:j}};let D=(T=L.filter(M=>M.overflows[0]<=0).sort((M,v)=>M.overflows[1]-v.overflows[1])[0])==null?void 0:T.placement;if(!D)switch(u){case"bestFit":{var P;const M=(P=L.filter(v=>{if(C){const k=U(v.placement);return k===x||k==="y"}return!0}).map(v=>[v.placement,v.overflows.filter(k=>k>0).reduce((k,I)=>k+I,0)]).sort((v,k)=>v[1]-k[1])[0])==null?void 0:P[0];M&&(D=M);break}case"initialPlacement":D=c;break}if(i!==D)return{reset:{placement:D}}}return{}}}};async function te(t,e){const{placement:n,platform:o,elements:i}=t,r=await(o.isRTL==null?void 0:o.isRTL(i.floating)),s=X(n),c=tt(n),f=U(n)==="y",l=["left","top"].includes(s)?-1:1,a=r&&f?-1:1,d=Z(e,t);let{mainAxis:m,crossAxis:u,alignmentAxis:g}=typeof d=="number"?{mainAxis:d,crossAxis:0,alignmentAxis:null}:{mainAxis:d.mainAxis||0,crossAxis:d.crossAxis||0,alignmentAxis:d.alignmentAxis};return c&&typeof g=="number"&&(u=c==="end"?g*-1:g),f?{x:u*a,y:m*l}:{x:m*l,y:u*a}}const ee=function(t){return t===void 0&&(t=0),{name:"offset",options:t,async fn(e){var n,o;const{x:i,y:r,placement:s,middlewareData:c}=e,f=await te(e,t);return s===((n=c.offset)==null?void 0:n.placement)&&(o=c.arrow)!=null&&o.alignmentOffset?{}:{x:i+f.x,y:r+f.y,data:{...f,placement:s}}}}},ne=function(t){return t===void 0&&(t={}),{name:"shift",options:t,async fn(e){const{x:n,y:o,placement:i}=e,{mainAxis:r=!0,crossAxis:s=!1,limiter:c={fn:w=>{let{x:p,y:x}=w;return{x:p,y:x}}},...f}=Z(t,e),l={x:n,y:o},a=await dt(e,f),d=U(X(i)),m=Mt(d);let u=l[m],g=l[d];if(r){const w=m==="y"?"top":"left",p=m==="y"?"bottom":"right",x=u+a[w],b=u-a[p];u=pt(x,u,b)}if(s){const w=d==="y"?"top":"left",p=d==="y"?"bottom":"right",x=g+a[w],b=g-a[p];g=pt(x,g,b)}const h=c.fn({...e,[m]:u,[d]:g});return{...h,data:{x:h.x-n,y:h.y-o,enabled:{[m]:r,[d]:s}}}}}},oe=function(t){return t===void 0&&(t={}),{name:"size",options:t,async fn(e){var n,o;const{placement:i,rects:r,platform:s,elements:c}=e,{apply:f=()=>{},...l}=Z(t,e),a=await dt(e,l),d=X(i),m=tt(i),u=U(i)==="y",{width:g,height:h}=r.floating;let w,p;d==="top"||d==="bottom"?(w=d,p=m===(await(s.isRTL==null?void 0:s.isRTL(c.floating))?"start":"end")?"left":"right"):(p=d,w=m==="end"?"top":"bottom");const x=h-a.top-a.bottom,b=g-a.left-a.right,R=q(h-a[w],x),y=q(g-a[p],b),C=!e.middlewareData.shift;let A=R,S=y;if((n=e.middlewareData.shift)!=null&&n.enabled.x&&(S=b),(o=e.middlewareData.shift)!=null&&o.enabled.y&&(A=x),C&&!m){const L=F(a.left,0),B=F(a.right,0),T=F(a.top,0),P=F(a.bottom,0);u?S=g-2*(L!==0||B!==0?L+B:F(a.left,a.right)):A=h-2*(T!==0||P!==0?T+P:F(a.top,a.bottom))}await f({...e,availableWidth:S,availableHeight:A});const W=await s.getDimensions(c.floating);return g!==W.width||h!==W.height?{reset:{rects:!0}}:{}}}};function Ft(t){const e=V(t);let n=parseFloat(e.width)||0,o=parseFloat(e.height)||0;const i=_(t),r=i?t.offsetWidth:n,s=i?t.offsetHeight:o,c=rt(n)!==r||rt(o)!==s;return c&&(n=r,o=s),{width:n,height:o,$:c}}function vt(t){return N(t)?t:t.contextElement}function G(t){const e=vt(t);if(!_(e))return H(1);const n=e.getBoundingClientRect(),{width:o,height:i,$:r}=Ft(e);let s=(r?rt(n.width):n.width)/o,c=(r?rt(n.height):n.height)/i;return(!s||!Number.isFinite(s))&&(s=1),(!c||!Number.isFinite(c))&&(c=1),{x:s,y:c}}const ie=H(0);function $t(t){const e=$(t);return!yt()||!e.visualViewport?ie:{x:e.visualViewport.offsetLeft,y:e.visualViewport.offsetTop}}function re(t,e,n){return e===void 0&&(e=!1),!n||e&&n!==$(t)?!1:e}function K(t,e,n,o){e===void 0&&(e=!1),n===void 0&&(n=!1);const i=t.getBoundingClientRect(),r=vt(t);let s=H(1);e&&(o?N(o)&&(s=G(o)):s=G(t));const c=re(r,n,o)?$t(r):H(0);let f=(i.left+c.x)/s.x,l=(i.top+c.y)/s.y,a=i.width/s.x,d=i.height/s.y;if(r){const m=$(r),u=o&&N(o)?$(o):o;let g=m,h=ht(g);for(;h&&o&&u!==g;){const w=G(h),p=h.getBoundingClientRect(),x=V(h),b=p.left+(h.clientLeft+parseFloat(x.paddingLeft))*w.x,R=p.top+(h.clientTop+parseFloat(x.paddingTop))*w.y;f*=w.x,l*=w.y,a*=w.x,d*=w.y,f+=b,l+=R,g=$(h),h=ht(g)}}return ct({width:a,height:d,x:f,y:l})}function At(t,e){const n=ut(t).scrollLeft;return e?e.left+n:K(z(t)).left+n}function Wt(t,e,n){n===void 0&&(n=!1);const o=t.getBoundingClientRect(),i=o.left+e.scrollLeft-(n?0:At(t,o)),r=o.top+e.scrollTop;return{x:i,y:r}}function se(t){let{elements:e,rect:n,offsetParent:o,strategy:i}=t;const r=i==="fixed",s=z(o),c=e?at(e.floating):!1;if(o===s||c&&r)return n;let f={scrollLeft:0,scrollTop:0},l=H(1);const a=H(0),d=_(o);if((d||!d&&!r)&&((Q(o)!=="body"||nt(s))&&(f=ut(o)),_(o))){const u=K(o);l=G(o),a.x=u.x+o.clientLeft,a.y=u.y+o.clientTop}const m=s&&!d&&!r?Wt(s,f,!0):H(0);return{width:n.width*l.x,height:n.height*l.y,x:n.x*l.x-f.scrollLeft*l.x+a.x+m.x,y:n.y*l.y-f.scrollTop*l.y+a.y+m.y}}function ce(t){return Array.from(t.getClientRects())}function le(t){const e=z(t),n=ut(t),o=t.ownerDocument.body,i=F(e.scrollWidth,e.clientWidth,o.scrollWidth,o.clientWidth),r=F(e.scrollHeight,e.clientHeight,o.scrollHeight,o.clientHeight);let s=-n.scrollLeft+At(t);const c=-n.scrollTop;return V(o).direction==="rtl"&&(s+=F(e.clientWidth,o.clientWidth)-i),{width:i,height:r,x:s,y:c}}function fe(t,e){const n=$(t),o=z(t),i=n.visualViewport;let r=o.clientWidth,s=o.clientHeight,c=0,f=0;if(i){r=i.width,s=i.height;const l=yt();(!l||l&&e==="fixed")&&(c=i.offsetLeft,f=i.offsetTop)}return{width:r,height:s,x:c,y:f}}function ae(t,e){const n=K(t,!0,e==="fixed"),o=n.top+t.clientTop,i=n.left+t.clientLeft,r=_(t)?G(t):H(1),s=t.clientWidth*r.x,c=t.clientHeight*r.y,f=i*r.x,l=o*r.y;return{width:s,height:c,x:f,y:l}}function Et(t,e,n){let o;if(e==="viewport")o=fe(t,n);else if(e==="document")o=le(z(t));else if(N(e))o=ae(e,n);else{const i=$t(t);o={x:e.x-i.x,y:e.y-i.y,width:e.width,height:e.height}}return ct(o)}function Bt(t,e){const n=Y(t);return n===e||!N(n)||J(n)?!1:V(n).position==="fixed"||Bt(n,e)}function ue(t,e){const n=e.get(t);if(n)return n;let o=et(t,[],!1).filter(c=>N(c)&&Q(c)!=="body"),i=null;const r=V(t).position==="fixed";let s=r?Y(t):t;for(;N(s)&&!J(s);){const c=V(s),f=xt(s);!f&&c.position==="fixed"&&(i=null),(r?!f&&!i:!f&&c.position==="static"&&!!i&&["absolute","fixed"].includes(i.position)||nt(s)&&!f&&Bt(t,s))?o=o.filter(a=>a!==s):i=c,s=Y(s)}return e.set(t,o),o}function de(t){let{element:e,boundary:n,rootBoundary:o,strategy:i}=t;const s=[...n==="clippingAncestors"?at(e)?[]:ue(e,this._c):[].concat(n),o],c=s[0],f=s.reduce((l,a)=>{const d=Et(e,a,i);return l.top=F(d.top,l.top),l.right=q(d.right,l.right),l.bottom=q(d.bottom,l.bottom),l.left=F(d.left,l.left),l},Et(e,c,i));return{width:f.right-f.left,height:f.bottom-f.top,x:f.left,y:f.top}}function me(t){const{width:e,height:n}=Ft(t);return{width:e,height:n}}function ge(t,e,n){const o=_(e),i=z(e),r=n==="fixed",s=K(t,!0,r,e);let c={scrollLeft:0,scrollTop:0};const f=H(0);if(o||!o&&!r)if((Q(e)!=="body"||nt(i))&&(c=ut(e)),o){const m=K(e,!0,r,e);f.x=m.x+e.clientLeft,f.y=m.y+e.clientTop}else i&&(f.x=At(i));const l=i&&!o&&!r?Wt(i,c):H(0),a=s.left+c.scrollLeft-f.x-l.x,d=s.top+c.scrollTop-f.y-l.y;return{x:a,y:d,width:s.width,height:s.height}}function mt(t){return V(t).position==="static"}function Dt(t,e){if(!_(t)||V(t).position==="fixed")return null;if(e)return e(t);let n=t.offsetParent;return z(t)===n&&(n=n.ownerDocument.body),n}function Nt(t,e){const n=$(t);if(at(t))return n;if(!_(t)){let i=Y(t);for(;i&&!J(i);){if(N(i)&&!mt(i))return i;i=Y(i)}return n}let o=Dt(t,e);for(;o&&zt(o)&&mt(o);)o=Dt(o,e);return o&&J(o)&&mt(o)&&!xt(o)?n:o||jt(t)||n}const he=async function(t){const e=this.getOffsetParent||Nt,n=this.getDimensions,o=await n(t.floating);return{reference:ge(t.reference,await e(t.floating),t.strategy),floating:{x:0,y:0,width:o.width,height:o.height}}};function pe(t){return V(t).direction==="rtl"}const we={convertOffsetParentRelativeRectToViewportRelativeRect:se,getDocumentElement:z,getClippingRect:de,getOffsetParent:Nt,getElementRects:he,getClientRects:ce,getDimensions:me,getScale:G,isElement:N,isRTL:pe};function Vt(t,e){return t.x===e.x&&t.y===e.y&&t.width===e.width&&t.height===e.height}function xe(t,e){let n=null,o;const i=z(t);function r(){var c;clearTimeout(o),(c=n)==null||c.disconnect(),n=null}function s(c,f){c===void 0&&(c=!1),f===void 0&&(f=1),r();const l=t.getBoundingClientRect(),{left:a,top:d,width:m,height:u}=l;if(c||e(),!m||!u)return;const g=ot(d),h=ot(i.clientWidth-(a+m)),w=ot(i.clientHeight-(d+u)),p=ot(a),b={rootMargin:-g+"px "+-h+"px "+-w+"px "+-p+"px",threshold:F(0,q(1,f))||1};let R=!0;function y(C){const A=C[0].intersectionRatio;if(A!==f){if(!R)return s();A?s(!1,A):o=setTimeout(()=>{s(!1,1e-7)},1e3)}A===1&&!Vt(l,t.getBoundingClientRect())&&s(),R=!1}try{n=new IntersectionObserver(y,{...b,root:i.ownerDocument})}catch{n=new IntersectionObserver(y,b)}n.observe(t)}return s(!0),r}function Ee(t,e,n,o){o===void 0&&(o={});const{ancestorScroll:i=!0,ancestorResize:r=!0,elementResize:s=typeof ResizeObserver=="function",layoutShift:c=typeof IntersectionObserver=="function",animationFrame:f=!1}=o,l=vt(t),a=i||r?[...l?et(l):[],...et(e)]:[];a.forEach(p=>{i&&p.addEventListener("scroll",n,{passive:!0}),r&&p.addEventListener("resize",n)});const d=l&&c?xe(l,n):null;let m=-1,u=null;s&&(u=new ResizeObserver(p=>{let[x]=p;x&&x.target===l&&u&&(u.unobserve(e),cancelAnimationFrame(m),m=requestAnimationFrame(()=>{var b;(b=u)==null||b.observe(e)})),n()}),l&&!f&&u.observe(l),u.observe(e));let g,h=f?K(t):null;f&&w();function w(){const p=K(t);h&&!Vt(h,p)&&n(),h=p,g=requestAnimationFrame(w)}return n(),()=>{var p;a.forEach(x=>{i&&x.removeEventListener("scroll",n),r&&x.removeEventListener("resize",n)}),d==null||d(),(p=u)==null||p.disconnect(),u=null,f&&cancelAnimationFrame(g)}}const De=dt,ye=ee,be=ne,Re=Zt,ve=oe,Lt=Qt,Ae=(t,e,n)=>{const o=new Map,i={platform:we,...n},r={...i.platform,_c:o};return Jt(t,e,{...i,platform:r})};var it=typeof document<"u"?E.useLayoutEffect:E.useEffect;function lt(t,e){if(t===e)return!0;if(typeof t!=typeof e)return!1;if(typeof t=="function"&&t.toString()===e.toString())return!0;let n,o,i;if(t&&e&&typeof t=="object"){if(Array.isArray(t)){if(n=t.length,n!==e.length)return!1;for(o=n;o--!==0;)if(!lt(t[o],e[o]))return!1;return!0}if(i=Object.keys(t),n=i.length,n!==Object.keys(e).length)return!1;for(o=n;o--!==0;)if(!{}.hasOwnProperty.call(e,i[o]))return!1;for(o=n;o--!==0;){const r=i[o];if(!(r==="_owner"&&t.$$typeof)&&!lt(t[r],e[r]))return!1}return!0}return t!==t&&e!==e}function Ht(t){return typeof window>"u"?1:(t.ownerDocument.defaultView||window).devicePixelRatio||1}function St(t,e){const n=Ht(t);return Math.round(e*n)/n}function gt(t){const e=E.useRef(t);return it(()=>{e.current=t}),e}function Le(t){t===void 0&&(t={});const{placement:e="bottom",strategy:n="absolute",middleware:o=[],platform:i,elements:{reference:r,floating:s}={},transform:c=!0,whileElementsMounted:f,open:l}=t,[a,d]=E.useState({x:0,y:0,strategy:n,placement:e,middlewareData:{},isPositioned:!1}),[m,u]=E.useState(o);lt(m,o)||u(o);const[g,h]=E.useState(null),[w,p]=E.useState(null),x=E.useCallback(v=>{v!==C.current&&(C.current=v,h(v))},[]),b=E.useCallback(v=>{v!==A.current&&(A.current=v,p(v))},[]),R=r||g,y=s||w,C=E.useRef(null),A=E.useRef(null),S=E.useRef(a),W=f!=null,L=gt(f),B=gt(i),T=gt(l),P=E.useCallback(()=>{if(!C.current||!A.current)return;const v={placement:e,strategy:n,middleware:m};B.current&&(v.platform=B.current),Ae(C.current,A.current,v).then(k=>{const I={...k,isPositioned:T.current!==!1};O.current&&!lt(S.current,I)&&(S.current=I,_t.flushSync(()=>{d(I)}))})},[m,e,n,B,T]);it(()=>{l===!1&&S.current.isPositioned&&(S.current.isPositioned=!1,d(v=>({...v,isPositioned:!1})))},[l]);const O=E.useRef(!1);it(()=>(O.current=!0,()=>{O.current=!1}),[]),it(()=>{if(R&&(C.current=R),y&&(A.current=y),R&&y){if(L.current)return L.current(R,y,P);P()}},[R,y,P,L,W]);const j=E.useMemo(()=>({reference:C,floating:A,setReference:x,setFloating:b}),[x,b]),D=E.useMemo(()=>({reference:R,floating:y}),[R,y]),M=E.useMemo(()=>{const v={position:n,left:0,top:0};if(!D.floating)return v;const k=St(D.floating,a.x),I=St(D.floating,a.y);return c?{...v,transform:"translate("+k+"px, "+I+"px)",...Ht(D.floating)>=1.5&&{willChange:"transform"}}:{position:n,left:k,top:I}},[n,c,D.floating,a.x,a.y]);return E.useMemo(()=>({...a,update:P,refs:j,elements:D,floatingStyles:M}),[a,P,j,D,M])}const Oe=t=>{function e(n){return{}.hasOwnProperty.call(n,"current")}return{name:"arrow",options:t,fn(n){const{element:o,padding:i}=typeof t=="function"?t(n):t;return o&&e(o)?o.current!=null?Lt({element:o.current,padding:i}).fn(n):{}:o?Lt({element:o,padding:i}).fn(n):{}}}},Se=(t,e)=>({...ye(t),options:[t,e]}),Pe=(t,e)=>({...be(t),options:[t,e]}),Te=(t,e)=>({...Re(t),options:[t,e]}),Me=(t,e)=>({...ve(t),options:[t,e]}),ke=(t,e)=>({...Oe(t),options:[t,e]});export{q as a,Me as b,Ee as c,De as d,Z as e,Te as f,V as g,ke as h,N as i,F as m,Se as o,rt as r,Pe as s,Le as u};
