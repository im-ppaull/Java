import { useState, type Dispatch, type SetStateAction } from "react";

const Login: React.FC<{
    setLogged: Dispatch<SetStateAction<boolean>>;
}> = ({ setLogged }) => {
    const [user, setUser] = useState<{ pin: null | number, id: null | number, accepted: boolean }>({
        pin: null,
        id: null,
        accepted: false
    });

    const handleLog = () => {
        if (!user.pin || !user.accepted || !user.id)
            return;

        console.log("logging in...")
    };

    return (
        <div className="w-[350px] h-fit absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 border border-neutral-300
            rounded-lg shadow-2xl p-5 flex flex-col gap-3">
            <h1 className="text-2xl text-white flex items-center justify-center">Log in</h1>

            <div>
                <label className="text-sm text-neutral-300">Personal identification number (PIN)</label>
                <input type="number" placeholder="Personal identification number"
                    className="w-full px-2 py-1 text-sm rounded-md"
                    onChange={(e) => setUser(prev => ({ ...prev, pin: Number(e.target.value) }))}/>
            </div>

            <div>
                <label className="text-sm text-neutral-300">ID card number</label>
                <input type="number" placeholder="ID card number"
                    className="w-full px-2 py-1 text-sm rounded-md"
                    onChange={(e) => setUser(prev => ({ ...prev, id: Number(e.target.value) }))}/>
            </div>

            <label className="flex items-center cursor-pointer gap-3">
                <input type="checkbox" id="log-accept-box" className="peer hidden" checked={user.accepted} 
                    onChange={(e) => setUser(prev => ({ ...prev, accepted: e.target.checked }))}/>
                
                <div className="w-5 h-5 rounded border border-white peer-checked:bg-neutral-400 
                    peer-checked:after:content-['✔'] peer-checked:after:text-white peer-checked:after:text-xs peer-checked:after:font-bold peer-checked:after:flex peer-checked:after:items-center peer-checked:after:justify-center">
                </div>

                <span className="text-sm text-neutral-300">I accept that my informations will be published</span>
            </label>

            <button className="bg-neutral-400 rounded-lg py-1 text-white
                hover:bg-zinc-400 transition-all" onClick={() => handleLog()}>Log</button>
        </div>
    )
};

export default Login;