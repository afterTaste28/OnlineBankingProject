import { createContext,useState } from "react";

export const UserContext = createContext();

const UserProvider = ({children})=>{
    const isSet = !!localStorage.getItem("token");
    const [isLoggedIn, setIsLoggedIn] = useState(isSet);
    return (
        <UserContext.Provider value={{isLoggedIn,setIsLoggedIn}}>
            {children}
        </UserContext.Provider>
    )
}
export default UserProvider;
