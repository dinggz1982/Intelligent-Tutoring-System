"use strict"; !
function(t, i) {
    var e = {
        id: "#cardArea",
        sid: ".card-item"
    };
    i.fn.cardArea = function(t) {
        var t = i.extend({},
        e, t);
        return this.each(function() {
            var e = i(t.id),
            n = e.find(t.sid);
            n.on("mouseenter",
            function() {
                i(this).addClass("active").siblings().removeClass("active")
            })
        })
    };
} (window, jQuery);